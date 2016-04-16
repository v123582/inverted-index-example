package hw2_part1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;



public class InvertIndex {
	public static void main(String[] args) throws Exception {
		 JobConf conf = new JobConf(InvertIndex.class);
		 
		 conf.setJobName("invertindex");
		 
	     
	     conf.setNumMapTasks(2);
		 conf.setNumReduceTasks(1);
		 
		 conf.setOutputKeyClass(Text.class);
	     conf.setOutputValueClass(Text.class);
	     
	     conf.setMapperClass(MyMapper.class);
	     conf.setCombinerClass(MyCombiner.class);
	     conf.setReducerClass(MyReducer.class);
	    
	     conf.setInputFormat(TextInputFormat.class);
	     conf.setOutputFormat(TextOutputFormat.class);
	     
	    
	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		 
	     JobClient.runJob(conf);   
	}
	
}
