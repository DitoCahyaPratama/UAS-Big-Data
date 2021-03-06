package org.doyatama;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DescMapper extends Mapper<Object, Text, Text, Text> {
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String txt = value.toString();
        String[] tokens = txt.split(",");
        String id = tokens[0].trim();
        String name = tokens[1].trim();
        if(name.compareTo("Desc") != 0){
            context.write(new Text(id), new Text(name));
        }
    }
}
