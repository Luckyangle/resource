package com.giant.resource;

import org.springblade.core.launch.BladeApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * Created by Lvzhihang
 * 2020/2/27
 * defination：
 */
@SpringCloudApplication
public class ResourceApplication {
    public static void main(String[] args) {
        BladeApplication.run("resource", ResourceApplication.class, args);
    }
}




