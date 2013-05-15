****************************************************************************
 _____             ______     _          
/  __ \            | ___ \   (_)         
| /  \/ __ _ _ __  | |_/ / __ _  ___ ___ 
| |    / _` | '__| |  __/ '__| |/ __/ _ \
| \__/\ (_| | |    | |  | |  | | (_|  __/
 \____/\__,_|_|    \_|  |_|  |_|\___\___|
 
______             _ _      _   _               _____           _                 
| ___ \           | (_)    | | (_)             /  ___|         | |                
| |_/ / __ ___  __| |_  ___| |_ _  ___  _ __   \ `--. _   _ ___| |_ ___ _ __ ___  
|  __/ '__/ _ \/ _` | |/ __| __| |/ _ \| '_ \   `--. \ | | / __| __/ _ \ '_ ` _ \ 
| |  | | |  __/ (_| | | (__| |_| | (_) | | | | /\__/ / |_| \__ \ ||  __/ | | | | |
\_|  |_|  \___|\__,_|_|\___|\__|_|\___/|_| |_| \____/ \__, |___/\__\___|_| |_| |_|
                                                       __/ |                      
                                                      |___/                       
___  ___             ______         _                         
|  \/  |             | ___ \       | |                  ___   
| .  . | __ _ _ __   | |_/ /___  __| |_   _  ___ ___   ( _ )  
| |\/| |/ _` | '_ \  |    // _ \/ _` | | | |/ __/ _ \  / _ \/\
| |  | | (_| | |_) | | |\ \  __/ (_| | |_| | (_|  __/ | (_>  <
\_|  |_/\__,_| .__/  \_| \_\___|\__,_|\__,_|\___\___|  \___/\/
             | |                                              
             |_|                                              

___  ___      _                 _   
|  \/  |     | |               | |  
| .  . | __ _| |__   ___  _   _| |_ 
| |\/| |/ _` | '_ \ / _ \| | | | __|
| |  | | (_| | | | | (_) | |_| | |_ 
\_|  |_/\__,_|_| |_|\___/ \__,_|\__|
                                    
****************************************************************************
Car Price Prediction System Using Map Reduce and Mahout
- By	Aniket Jadhav, Vaibhav Shukla, Arundhati Tambe
****************************************************************************

Technologies:
Nutch 1.6, MapReduce in Java, Mahout.

Use:
For selling a used car, its price can be predicted by giving 
some attributes. e.g. Car Model, total miles, engine type. Trained data 
will be provided to our system to predict the price for new data.

Development Process:
Used Nutch to crawl car data from 52 states which is on craigslist.org. 
Over 0.3 million records were fetched. The content was then pruned using 
two MapReduce Jobs. The first MapReduce cleaned the data removing 
unwanted unicode symbols and incomplete data (information without 
car model or total miles or engine or price).

The Second MapReduce extracted the required attributes and emitted 
in tsv format. This tsv was then provided to a Naïve Based Classifier 
in Mahout. A classification model was built from the training data.

This models predicts the price when attributes like car model model,
miles, engine was provided.

_____________________________________________________________________________

Contents of the folders
_____________________________________________________________________________

The "Data" folder has 4 files.

carmodels.txt	->	Represents most car names. (Created by me)
InputMapreduce1	->	Created by Web Crawler (Nutch 1.6)
InputCarInfo	->	This is output of MapReduce Job 1
OutputMapReduce	->	This is output of MapReduce Job 2

*****************************************************************************

MapReduce Job 1

Filename: SegScan/src/

Input:	InputMapreduce1
Output:	InputCarInfo

Execute as follows

Compile the program using eclipse with hadoop plugin

Execute on commandline (terminal) using following command
hadoop jar SegScan.jar SegScan Data/input1 ./input2/

*****************************************************************************

MapReduce Job 2

Filename: CarInformation/src

Input: InputCarInfo
Output: OutputMapReduce

Execute as follows

Compile the program using eclipse with hadoop plugin

Execute on commandline (terminal) using following command
hadoop jar CarInfo.jar CarInfo Data/input2 ./Output/

****************************************************************************

You will get a tsv. Use this tsv in Mahout to create a Naïve Based Classifier
to create a classification model.

****************************************************************************

  _______ _                 _     __     __         
 |__   __| |               | |    \ \   / /         
    | |  | |__   __ _ _ __ | | __  \ \_/ /__  _   _ 
    | |  | '_ \ / _` | '_ \| |/ /   \   / _ \| | | |
    | |  | | | | (_| | | | |   <     | | (_) | |_| |
    |_|  |_| |_|\__,_|_| |_|_|\_\    |_|\___/ \__,_|
                                                    
                                                    
**********************************************************- bY AniKet Jadhav.