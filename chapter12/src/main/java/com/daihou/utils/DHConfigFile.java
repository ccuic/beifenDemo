package com.daihou.utils;

import com.course.model.AuthName;
import com.course.model.InterfaceName;
import com.daihou.model.DHInterfaceName;
import com.daihou.model.DaiHou_API;
import java.util.Locale;
import java.util.ResourceBundle;

public class DHConfigFile {

   private static ResourceBundle bundle= ResourceBundle.getBundle("application", Locale.CHINA);;

    public static String getProp(DaiHou_API name){
        String result = "";
        if(name == DaiHou_API.AUTH){
            result = bundle.getString("AUTH");
        }
        return result;
    }

    public static String getUrl(DHInterfaceName name){
        String address = bundle.getString("DaiHou_URL");
        String uri = "";
        String testUrl;
        if(name == DHInterfaceName.PINGFEN){
            uri = bundle.getString("pingfen.uri");
        }

        if(name == DHInterfaceName.DAORU){
            uri = bundle.getString("login.uri");
        }

        if(name == DHInterfaceName.DAOCHU){
            uri = bundle.getString("updateUserInfo.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
}
