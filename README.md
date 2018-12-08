# AmazonDataNetwork

Data analysis on over 550,000 items from Amazon

Data received from: http://snap.stanford.edu/data/amazon-meta.html


### Parse.java
File is used to parse out information for each item in file.

A single item in the data file looks like so: 
```
Id   1
ASIN 0827229534
  title Patterns of Preaching A Sermon Sampler
  group Book
  salesrank 396585
  similar 5  0804215715  156101074X  0687023955  0687074231  082721619X
  categories 2
   Books[283155]   Subjects[1000]   Religion & Spirituality[22]   Christianity[12290]   Clergy[12360]   Preaching[12368]   
   Books[283155]   Subjects[1000]   Religion & Spirituality[22]   Christianity[12290]   Clergy[12360]   Sermons[12370]   
  reviews total 2  downloaded 2  avg rating 5
    2000-7-28  customer A2JW67OY8U6HHK  rating 5  votes  10  helpful   9
    2003-12-14  customer A2VE83MZF98ITY  rating 5  votes   6  helpful   5
```

We split the data file into 2 files, each over 500 MB; however, GitHub does not allow any files larger than 100 MB.

Usage of this `Parse.java` file include: 

1) Separation of items into corresponding group (book, music, dvd, video)
2) Formatting data into a structure which would be accepted by Gephi or Cytoscape (csv or sif)
3) Calculation of average and/or total values for each catetory (size, rating, number of reviews, salesrank)
    
