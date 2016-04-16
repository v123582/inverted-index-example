CS542000 Cloud Programming
Homework 2: Inverted Index
資工碩一‭ ‬張維元‭ ‬103062590
May 17‭, ‬2015

Instruction
1.	Part1
ν	javac -classpath hadoop-core-1.0.3.jar -d class1/ part1/*
ν	jar -cvf InvertIndex.jar -C class1 .
ν	hadoop jar InvertIndex.jar hw2_part1.InvertIndex Hw2-Part1-input Hw2-Part1-output
ν	Input: hdfs/input/*
ν	Output: hdfs/part-00000

2.	part2
ν	javac -classpath hadoop-core-1.0.3.jar -d class2/ part2/*
ν	jar -cvf Retrieval.jar -C class2/ .
ν	hadoop jar Retrieval.jar hw2_part2.Retrieval
ν	Input: hdfs/part-00000
ν	Output: local/SampleOutput-retrieval.txt

Design
1.	Part1
ν	In the mapper, each word in the file and its filename as input, produce the key-value pair ((file, filename), fileWordcount), where value is Wordcount in this file.
ν	In the combiner, calculate the TF and transform to the key: file – value: (filename, TF) pair, and pass it to reducer.
ν	In the reducer: calculate the IDF and output this form:
ν	(word\tIDF, TF1\tfilename1, TF2\tfilename2, …)
2.	part2
ν	In Retrieval, we will split the query by blank, and then search the hdfs inverted index table individually.
ν	Calculate and merge(or) the results to the SampleOutput file.

Questions
1.	How many #phases you used to run map reduce in part1? Is there any other way to do it? What's the pros and cons?
ν	I used one pass mapReduce in part1. 
ν	Many other ways:
ν	TF can be calculated in mapper. 
υ	Pro: No combiner. 
υ	Con: The mapper needs to keep the counts and offsets of all kinds of words. It wastes more memory.
ν	TF can be calculated in the reducer. 
υ	Con: That needs one more map reduce pass for calculating document frequency.

2.	What's your extension? What's the most difficult part in your implementation?
ν	Query can ignore case
ν	In Part1, all words are converted to lowercase first, and the query in Part2 are converted to lowercase.
ν	Filter the stop words
ν	I created the StopWords list to filter those useless notations. Omit the word in StopWords when reading words in Part1.

3.	How do you filter those useless notations? If we need to search these special notations, how to modify your filter?
ν	Omit the notations using Regular Expression
ν	replaceAll("\\W", " ") to filter the useless notations

