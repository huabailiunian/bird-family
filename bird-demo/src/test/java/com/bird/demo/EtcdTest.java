package com.bird.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author master
 * @date 2020-04-30 15:08
 */
public class EtcdTest {

    private static final String url = "http://127.0.0.1:2379";

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void connTest() throws ExecutionException, InterruptedException, JsonProcessingException {
//        Client client = Client.builder().endpoints(url).build();
//        KV kvClient = client.getKVClient();
//        ByteSequence key = ByteSequence.from("name".getBytes(Const.CHARSET_UTF8));
//        ByteSequence value = ByteSequence.from("Bob".getBytes(Const.CHARSET_UTF8));
////        kvClient.put(key,value).get();
//
//        CompletableFuture<GetResponse> future = kvClient.get(key);
//        List<KeyValue> values = future.get().getKvs();
//        values.stream().forEach(v -> {
//            byte[] bytes = v.getValue().getBytes();
//            System.out.println(new String(bytes, Const.CHARSET_UTF8));
//        });

    }
}
