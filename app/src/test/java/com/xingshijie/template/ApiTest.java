package com.xingshijie.template;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xingshijie.common.network.Api.ApiServiceImp;
import com.xingshijie.common.network.model.Turing;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Word Xing  on 2016/5/10.
 */
public class ApiTest {

//    @Rule
//    public final MockWebServer server = new MockWebServer();
    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder().create();
    }

    @Test
    public void anInterface() throws IOException, InterruptedException {
//        server.enqueue(new MockResponse().setBody("{\"name\":\"value\"}"));
        Call<Turing> turingCall = ApiServiceImp.turing("我累个槽");
        Response<Turing> response = turingCall.execute();
        assertThat(response.code()).isEqualTo(200);
        System.out.println(gson.toJson(response.body()));
    }

    @Test
    public void strings() throws IOException, InterruptedException {
//        server.enqueue(new MockResponse().setBody("{\"name\":\"value\"}"));
        Call<String> images= ApiServiceImp.qrCode();
        Response<String> response = images.execute();
        assertThat(response.code()).isEqualTo(200);
        System.out.println(JSON.toJSONString(response.body(),true));
    }
}
