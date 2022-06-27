package com.doyatama.hadoop.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
@RequestMapping("/api/hadoop")
public class MapReduceController {

    @GetMapping("/")
    public String[] getHadoop() throws IOException {
        String uri = "hdfs://192.168.0.12:9000/DitoCahyaPratama/Hasil/part-r-00000";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String result[];
        try{
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
        result = out.toString().split("\n");
        return result;
    }
}
