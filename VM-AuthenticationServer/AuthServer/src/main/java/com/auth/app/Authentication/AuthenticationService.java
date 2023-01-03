package com.auth.app.Authentication;

import com.auth.app.Authentication.domain.*;
import org.json.JSONObject;

public class AuthenticationService {
    private Authentication auth;

    public AuthenticationService() {
        try {
            auth = new Authentication();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public JSONObject doSomething(String ip, String id, String pass) {
        JSONObject jsonObj = new JSONObject();
        if (!auth.checkPass(ip, id, pass)) {
            // return empty, user or pass not valid!
            return jsonObj;
        }

        try {
            jsonObj = auth.tokenGenerator(id);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jsonObj;
    }
}