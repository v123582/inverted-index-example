package hw2_part1;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class MyMapper extends MapReduceBase implements Mapper<Object, Text, Text, Text>{

        public int count=0;
        public String[] stopWords = {"a", "the", "an", "are", "is"};
        @Override
        public void map(Object key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
                // TODO Auto-generated method stub
                FileSplit filesplit = (FileSplit) reporter.getInputSplit();
                String fileName = filesplit.getPath().getName();
                String line = value.toString().replaceAll("\\W", " ");

                Map<String, Integer> filecount = new HashMap<String, Integer>();
                filecount.put( "1kinghenryiv", 8181);
                filecount.put( "1kinghenryvi", 14664);
                filecount.put( "2kinghenryiv",    16979);
                filecount.put( "2kinghenryvi",    16814);
                filecount.put( "3kinghenryvi",    16672);
                filecount.put( "allswellthatendswell",    14196);
                filecount.put( "antonyandcleopatra",  17134);
                filecount.put( "asyoulikeit", 13052);
                filecount.put( "comedyoferrors",  9337);
                filecount.put( "coriolanus",  17282);
                filecount.put( "cymbeline",   17332);
                filecount.put( "glossary",    6932);
                filecount.put( "hamlet",  19045);
                filecount.put( "juliuscaesar",    12490);
                filecount.put( "loverscomplaint", 1540);
                filecount.put( "loveslabourslost",    14088);
                filecount.put( "othello", 16345);
                filecount.put( "periclesprinceoftyre",    11843);
                filecount.put( "rapeoflucrece",   9534);
                filecount.put( "romeoandjuliet",  16382);
                filecount.put( "sonnets", 11034);
                filecount.put( "various", 2222);
                filecount.put( "venusandadonis",  6193);
                filecount.put( "winterstale", 7192);

                StringTokenizer tokenizer = new StringTokenizer(line);
                while(tokenizer.hasMoreTokens()) {
                        String Token = tokenizer.nextToken();
                        int count = Token.countTokens()
                        Boolean flag = false;
                        for(int i=0; i<=4; i++){
                            if(stopWords[i].equals(Token.toLowerCase()))
                                flag = true;
                        }
                        if(flag) continue;
                        Text mykey = new Text(Token.toLowerCase() + "\t" + fileName );
                        Text myvalue = new Text(Integer.toString(filecount.get(fileName)));
                        output.collect(mykey, myvalue);
                }

        }

}