import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.List;

public class Generate {
    // find the ratio between all cases to see how much cases each of them to fill the 50k
    // for example, THE 100K, is the amount to be shared in this case.
    // ratio per 100k for each disease of the 5 diseases (Ebola, Brain cancer, Lung cancer, Covid, SARS) is as follows respectiveley
    //  100 : 80 : 90:  150: 70 : 37.5 : 7.5 : 62.8 : 360.68 : 8.04 .. total =  476.52 //966.52
    // value of 1 part  = 209.85478049 //51.73
    // number of cases for each disease in the 100k population is:
    // 7,869 - 1,574 - 13,178 - 75,690 - 1,686 .. since at least 50% should be healthy. so we will divide the number of cases by 2.
    // cases in 50k will be as follows : 3935 - 787 - 6590 - 37,845 - 844 .. This is for male gender ----------
    //
    //  Heart : kideny: Diabetes : Influenza : H1n1 : Ebola : brain c - lung c : covid : SARS
    //  5173 : 4138 : 4656 : 7760 : 3621 : 1940 : 388 : 3249 : 18658 : 416
    // For female.
    // 37.5 : 5.3 : 49.4 : 387.76 : 8.04 .. total = 488
    // value of 1 part  = 204.91803278
    // number of cases for each disease in 100k population:
    // 7684 - 1086 - 10123 - 79459 - 1647
    //
    // 3842 - 543 - 5061 - 39,729 - 823 ... For the female gender ----------------

    // the number of each class wil be as follows [ 0 , 1 , 2 , 3 , 4 , 5] .. for Ebola , Brain Cancer, Lung cancer , Covid , Sars and healthy people respectively

    static int _data = 10;
    static long patientID = 500000000;
    static int fileNumber = 1;

    //place to store the data in
    static String location = "Data/emr";


    static String fileName = location + "1.csv";
    static boolean gender = true; //male
    FileWriter fileWriter;

    File myObj;
    HeartFailureGen heartFailureGen;
    KidneyFailure kidneyFailure;
    DiabetesGen diabetesGen;
    InfluenzaGen influenzaGen;
    H1N1Gen h1N1Gen;
    Covid19Gen covid19Gen;
    EbolaGen ebolaGen;
    BrainCancerGen brainCancerGen;
    LungCancerGen lungCancerGen;
    SARSGen sarsGen;
    HealthyGen healthyGen;



    //number of files to generate ..
    public void start() throws IOException {

        for (int i = 0; i < _data; i++) {
            createFile();
            runGens();

            //change male to female or vice versa
            gender = !gender;
        }
    }
    public void runGens() throws IOException {

        writer();

        heartFailureGen.start();
        kidneyFailure.kidneyStart();
        diabetesGen.diabetesStart();
        influenzaGen.fluStart();
        h1N1Gen.h1n1Start();
        ebolaGen.EbolaStart();
        brainCancerGen.brainCancerStartFunc();
        lungCancerGen.lungCancerStartFunc();
        covid19Gen.CovidStart();
        sarsGen.SARSGenStartFunc();
        healthyGen.HealthyPeopleStartFunc();

        fileWriter.close();

    }

    public void createFile() throws IOException {

        myObj = new File(fileName);
        try {

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        fileNumber = fileNumber+1;
        fileName = location + fileNumber+".csv";

        fileWriter = new FileWriter(myObj);
        //give the file to the generator to put in the data
        defineEMRs();

    }

    public List<String> writeLabels() throws IOException {


        List<String> columnLabels = new ArrayList<>(79);

        columnLabels.add("PatientID");
        columnLabels.add("Age");
        columnLabels.add("Gender");
        columnLabels.add("RBC (*10^6/ mL)");
        columnLabels.add("RBCDistWidth (%)");
        columnLabels.add("Hemoglobin (g/dL)");
        columnLabels.add("Hct (%)");
        columnLabels.add("WBC (*10^6/mL)");
        columnLabels.add("NEU (/mL)");
        columnLabels.add("Lymph (/mL)");
        columnLabels.add("Mono (/mL)");
        columnLabels.add("Eos (/mL)");
        columnLabels.add("Baso (/mL)");
        columnLabels.add("Plt (*10^3/mL");
        columnLabels.add("MPV (fL)");
        columnLabels.add("PltDistWidth (%)");
        columnLabels.add("Sodium (mEq/L)");
        columnLabels.add("Potassium (mEq/L)");
        columnLabels.add("Chloride (mEq/L)");
        columnLabels.add("BiCarbonate (mEq/L)");
        columnLabels.add("Serum-Creatinine (mg/dL)");
        columnLabels.add("GFR");
        columnLabels.add("BUN (mg/dL)");
        columnLabels.add("ACR (mg/g)");
        columnLabels.add("BNP (pg/mL)");
        columnLabels.add("EF (%)");
        columnLabels.add("Fasting Blood Sugar (mg/dL)");
        columnLabels.add("Random Blood sugar (mg/dL)");
        columnLabels.add("HemoglobinA1c (%)");
        columnLabels.add("RIDTs (+ve OR -ve)");
        columnLabels.add("RMA (+ve OR -ve)");
        columnLabels.add("RT_PCR (+ve OR -ve)");
        columnLabels.add("Rapid Cell Culture Test (+ve OR -ve)");
        columnLabels.add("VirtualTissueCell (+ve OR -ve)");
        columnLabels.add("Immunofluorescence (+ve OR -ve)");
        columnLabels.add("PCR (+ve OR -ve)");
        columnLabels.add("NAATs (+ve OR -ve)");
        columnLabels.add("RapidEbolaAntigenTest (+ve OR -ve)");
        columnLabels.add("LaboratoryAntigenTest (+ve OR -ve)");
        columnLabels.add("EbolaAntigenTesting (+ve OR -ve)");
        columnLabels.add("LP (+ve OR -ve)");
        columnLabels.add("Biopsy (+ve OR -ve)");
        columnLabels.add("X-Ray (+ve OR -ve)");
        columnLabels.add("CT_SCAN (+ve OR -ve)");
        columnLabels.add("PET_CT (+ve OR -ve)");
        columnLabels.add("Bronchoscopy (+ve OR -ve)");
        columnLabels.add("Weightloss/PoorAppetite (0-5)");
        columnLabels.add("Breathlessness (0-5)");
        columnLabels.add("Fatigue (0-5)");
        columnLabels.add("Swollen Ankles and Legs (0-5)");
        columnLabels.add("Itchy Skin (0-5)");
        columnLabels.add("Fever (0-5)");
        columnLabels.add("Tiredness (0-5)");
        columnLabels.add("Cough (0-10)");
        columnLabels.add("RunnyNose (0-5)");
        columnLabels.add("Constant/Excessive Hunger (0-5)");
        columnLabels.add("Excessive Thirst (0-5)");
        columnLabels.add("Frequent Urination (0-5)");
        columnLabels.add("Dry Mouth (0-5)");
        columnLabels.add("Blurred Vision (0-5)");
        columnLabels.add("Sore throat (0-5)");
        columnLabels.add("Headache (0-5)");
        columnLabels.add("Body Aches (0-5)");
        columnLabels.add("Insonmia (0-5)");
        columnLabels.add("Nausea (0-5)");
        columnLabels.add("Vomiting (0-5)");
        columnLabels.add("Memoryloss (0-5)");
        columnLabels.add("Drowsiness (0-5)");
        columnLabels.add("Diarrhea (0-5)");
        columnLabels.add("Blood Coughing (YES/NO)");
        columnLabels.add("Blood in urine (YES/NO)");
        columnLabels.add("Rash (0-5)");
        columnLabels.add("Chills (0-5)");
        columnLabels.add("Stuffy Nose (0-5)");
        columnLabels.add("Conjunctivitis (YES/NO)");
        columnLabels.add("Red/Watery Eyes (YES/NO)");
        columnLabels.add("Confusion (0-5)");
        columnLabels.add("Loss of taste AND/OR Smell (YES/NO)");
        columnLabels.add("Class Name(Y)");


        return columnLabels;

    }

    private void writer() throws IOException {

        List<String> record = writeLabels();
        String str = String.join(",", record) + "\n";
        fileWriter.write(str);
    }


    private void defineEMRs() throws IOException {

        heartFailureGen = new HeartFailureGen(myObj,fileWriter);
        kidneyFailure = new KidneyFailure(myObj,fileWriter);
        diabetesGen = new DiabetesGen(myObj,fileWriter);
        influenzaGen = new InfluenzaGen(myObj, fileWriter);
        h1N1Gen = new H1N1Gen(myObj, fileWriter);
        covid19Gen = new Covid19Gen(myObj,fileWriter);
        ebolaGen = new EbolaGen(myObj,fileWriter);
        brainCancerGen = new BrainCancerGen(myObj,fileWriter);
        lungCancerGen = new LungCancerGen(myObj,fileWriter);
        sarsGen = new SARSGen(myObj,fileWriter);
        healthyGen = new HealthyGen(myObj,fileWriter);
    }


}
