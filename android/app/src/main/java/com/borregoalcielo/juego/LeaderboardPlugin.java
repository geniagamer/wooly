package com.borregoalcielo.juego;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;

/**
 * Tabla de récords mundial con Google Play Juegos.
 * Se activa solo cuando pones tus IDs en res/values/strings.xml
 * (game_services_project_id y leaderboard_height). Ver RECORDS.md.
 */
@CapacitorPlugin(name = "Leaderboard")
public class LeaderboardPlugin extends Plugin {
    private boolean enabled = false;
    private String leaderboardId = "";

    @Override
    public void load() {
        try {
            String appId = getContext().getString(R.string.game_services_project_id);
            leaderboardId = getContext().getString(R.string.leaderboard_height);
            enabled = appId != null && !appId.trim().equals("0") && !leaderboardId.equals("PENDIENTE");
            if (enabled) PlayGamesSdk.initialize(getContext());
        } catch (Exception e) {
            enabled = false;
        }
    }

    @PluginMethod
    public void available(PluginCall call) {
        JSObject r = new JSObject();
        r.put("available", enabled);
        call.resolve(r);
    }

    @PluginMethod
    public void submit(PluginCall call) {
        if (!enabled) { call.resolve(); return; }
        Double d = call.getDouble("score", 0.0);
        final long score = d == null ? 0L : d.longValue();
        PlayGames.getGamesSignInClient(getActivity()).isAuthenticated().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().isAuthenticated()) {
                PlayGames.getLeaderboardsClient(getActivity()).submitScore(leaderboardId, score);
            }
            call.resolve();
        });
    }

    @PluginMethod
    public void show(PluginCall call) {
        if (!enabled) { call.reject("not configured"); return; }
        GamesSignInClient signIn = PlayGames.getGamesSignInClient(getActivity());
        signIn.isAuthenticated().addOnCompleteListener(task -> {
            boolean ok = task.isSuccessful() && task.getResult().isAuthenticated();
            if (ok) { openBoard(call); return; }
            signIn.signIn().addOnCompleteListener(t2 -> {
                if (t2.isSuccessful() && t2.getResult().isAuthenticated()) openBoard(call);
                else call.reject("sign-in failed");
            });
        });
    }

    private void openBoard(PluginCall call) {
        PlayGames.getLeaderboardsClient(getActivity())
            .getLeaderboardIntent(leaderboardId)
            .addOnSuccessListener(intent -> {
                getActivity().startActivityForResult(intent, 9004);
                call.resolve();
            })
            .addOnFailureListener(e -> call.reject(e.getMessage()));
    }
}
