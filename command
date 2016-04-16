ssh 103062590@140.114.91.199


rm SampleOutput-Retrieval-hdfs.txt

hadoop dfs -rmr Hw2-Part1-output
javac -classpath hadoop-core-1.0.3.jar -d class1/ part1/*
jar -cvf InvertIndex.jar -C class1 .
hadoop jar InvertIndex.jar hw2_part1.InvertIndex Hw2-Part1-input Hw2-Part1-output

javac -classpath hadoop-core-1.0.3.jar -d class2/ part2/*
jar -cvf Retrieval.jar -C class2/ .
hadoop jar Retrieval.jar hw2_part2.Retrieval
cat SampleOutput-Retrieval-hdfs.txt

rm -rf Hw2-Part1-output
hadoop dfs -get Hw2-Part1-output .

rm SampleOutput-Table.txt
cp Hw2-Part1-output/part-00000 SampleOutput-Table.txt

javac -classpath hadoop-core-1.0.3.jar -d class2/ part2/*
jar -cvf Retrieval.jar -C class2/ .
hadoop jar Retrieval.jar hw2_part2.Retrieval
cat SampleOutput-Retrieval.txt


jar -cvf Cat.jar -C Cat/ .
hadoop jar Retrieval.jar hw2_part2.Retrieval

* hadoop dfs -ls
* hadoop dfs -get Hw2-Part1-output .


* jar -cvf Retrieval.jar -C class2/ .
* java -cp Retrieval.jar HW1Part2.Retrieval


Your input Query is : AEneas ALEXANDER apple
We will output top 10 result
Rank	Filename	Score	Query
1	troilusandcressida	57.00792709105678	aeneas
2	troilusandcressida	8.143989584436683	alexander
3	kinghenryv	5.922901515953951	alexander
4	hamlet	3.7018134474712197	alexander
5	glossary	2.4082399653118496	apple
6	2kinghenryvi	2.2210880684827314	alexander
7	loveslabourslost	2.2210880684827314	alexander
8	2kinghenryiv	1.806179973983887	apple
9	2kinghenryvi	1.4807253789884878	aeneas
10	tamingoftheshrew	1.2041199826559248	apple


hadoop jar InvertIndex.jar hw1.InvertIndex Hw2-Part1-input Hw2-Part1-output-new
user/103062590/Hw2-Part1-input/
