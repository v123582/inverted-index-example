package hw2_part1;

import org.apache.hadoop.mapred.MapReduceBase;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.IntWritable;

public class MyCombiner extends MapReduceBase
implements Reducer<Text,Text,Text,Text>{
	
	public int count = 0;
	public void reduce(Text key, Iterator<Text> values,
			OutputCollector<Text,Text> output, Reporter reporter) throws IOException {
		
		String temp = null;
		Text text = null;
		
		double countTF = 0;
		Text txt = new Text();
		while(values.hasNext()){
		    countTF += (double) 1/Integer.valueOf(values.next().toString()).intValue();
        }

		String mykey =  key.toString().split("\\t")[0];
		String myvalue = key.toString().split("\\t")[1] + "\t" + countTF;
		output.collect(new Text(mykey), new Text(myvalue));

	}
}


