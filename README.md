Code Assessment
=============

The aim of this assessment is to 
* Demonstrate a program using Scala to identify all anagram words given in the input file i.e. words.txt
* Once identified, write the pair of anagram words to an output file i.e. output_anagram.txt
* Create test cases and cover a few edge cases too


## Prepare Environment
- JDK 1.8
- Scala 1.12
- Editor - Intellij or Eclipse


###  Setup
Download the git repo
```
git clone https://github.com/chandanparihar1986/anagram_assessment_030722.git
```


###  How to Build

The jar is built using the Build Artifects option under Build in the IntelliJ IDE
Please make sure the SDK is selected under the SDK module section (project settings -> modules -> dependencies)
Else it will keep popping up SDK related issues.


###  How to Run
    java -cp /Users/cparihar/Documents/Projects/anagram_assessment/out/artifacts/anagram_assessment_jar/anagram_assessment.jar com.myorg.programs.anagram


### Unit Test Instructions
- Test cases can be found here -> src/main/test/anagram_test

###  Sample output
    Abel able
    aboard abroad
    accrues accuser
    abode adobe
    Adler alder
    Alexander Alexandre


###  Out of scope
Parameterization using confile file 
   * All hardcoded values must be passed through the config file i.e. source, target, file path, etc.

CICD - stage or prod deployment

END to End pipeline   (Ingestion, Integration, Processing, Storage & Visualization)

###  Performance
To check if the two words are anagram or not, we could have used the sorting approach too but that would be a bit expensive 
as time compilexity for sorting a string is o(n log n) compared to o(n) for the hashmap object we have used in this solution. 
