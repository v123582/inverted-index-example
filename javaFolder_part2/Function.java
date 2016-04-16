package hw2_part2;

import java.io.*;
import java.util.*;
import java.net.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class Function {

	public void rankOR(String input) throws Exception{


		input = input.replaceAll("\\W", " "); 
		String tempinput = input;
		tempinput = tempinput.toLowerCase();
		String inputquery[] = tempinput.split("\\s");
		ArrayList<String> queryList = new ArrayList<String>();
		for(int i=0;i<inputquery.length;i++){
			queryList.add(inputquery[i].trim());
		}
		
		Path pt=new Path("hdfs://10.1.114.6:8100/user/103062590/Hw2-Part1-output/part-00000");
        FileSystem fs = FileSystem.get(new Configuration());
        BufferedReader tr=new BufferedReader(new InputStreamReader(fs.open(pt)));
		String str = null;
		double N = 24;
		int clock = 0;
		tr.close();
		
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
		ArrayList<String> matchlist = new ArrayList<String>();
		ArrayList<TFIDFMember> memberList = new ArrayList<TFIDFMember>();
		String line = null ,Query = null;
		double TF = 0.0 , IDF = 0.0 , score = 0.0;
		int countquery=0;
		while((line=br.readLine())!=null && countquery<queryList.size()){
			if(queryList.contains(line.split("\\t")[0])==true){
				matchlist.add(line.substring(0, line.lastIndexOf(",")));
				countquery++;
			}
		}
		br.close();

		for(int i=0;i<matchlist.size();i++){
			String temparr[] = matchlist.get(i).split(",");
			Query = temparr[0].split("\\t")[0]; 
			IDF = Double.parseDouble(temparr[0].split("\\t")[1]); 
			
			for(int j=1;j<temparr.length;j++){
				TFIDFMember tfidf = new TFIDFMember();
				tfidf.setFilename(temparr[j].split("\\t")[0]); 
				tfidf.setQuery(Query); 
				TF = Double.parseDouble(temparr[j].split("\\t")[1]); 
				score = TF * Math.log10(N/IDF); 
				tfidf.setTF(TF); 
				tfidf.setScore(score); 
				
				memberList.add(tfidf);
			}

		}
		Collections.sort(memberList);
		
		
		
		BufferedWriter wr = new BufferedWriter(new FileWriter("SampleOutput-Retrieval-hdfs.txt"));
		wr.write("Your input Query is : " + input +"\n");
		wr.write("We will output top 10 result"+"\n");
		wr.write("Rank"+"\t"+"Filename"+"\t"+"Score"+"\t"+"Query"+"\n");
		if(memberList.size()<=10){
			for(int i=0;i<memberList.size();i++){
				int j=i+1;
				wr.write(j+"\t"+memberList.get(i).getFilename()+"\t"+memberList.get(i).getScore()+"\t"+memberList.get(i).getQuery()+"\n");
			}
		}
		else if(memberList.size()>10){
			for(int i=0;i<10;i++){
				int j=i+1;
				wr.write(j+"\t"+memberList.get(i).getFilename()+"\t"+memberList.get(i).getScore()+"\t"+memberList.get(i).getQuery()+"\n");
			}
		}
		wr.close();
	}
}
