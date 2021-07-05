import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class EbolaGen {


    File file;
    FileWriter writer;
    public EbolaGen(File file, FileWriter writer){
        this.file = file;
        this.writer = writer;
    };

    public void EbolaStart() throws IOException {

        int capacity = (Generate.gender? 1940 : 1940);
        List<List<String>> allRecords = new ArrayList<List<String>>(capacity); //76 column & 37,845 row


        for (int i  = 0; i < capacity; i++){
            EbolaPatientGenerator(allRecords);
            write(allRecords.get(i),writer);
        }
    }

    private void write(List<String> record, Writer writer) throws IOException {

        String str = String.join(",", record) + "\n";
        //for (String rec: record) {
        //writer.write(rec+",");
        //}
        writer.write(str);
    }

    private void EbolaPatientGenerator(List<List<String>> allRecords){

        List<String> record = new ArrayList<>(79);
        String patientID = Generate.patientID + "";
        record.add(patientID);

        Generate.patientID = Generate.patientID + 1;

        int Age = this.nextInt(1,80);
        record.add(Age+"");

        String gender = (Generate.gender? "Male" : "Female");
        record.add(gender);

        double RBC = (Generate.gender? truncate(this.nextFloat(4.5,5.9)) : truncate(this.nextFloat(4.1,5.1)));
        record.add(RBC+"");

        double RBCDistributionWidth = (Generate.gender? truncate(this.nextFloat(11.8,14.5)) : truncate(this.nextFloat(12.2,16.1)));
        record.add(RBCDistributionWidth+"");

        double Hemoglobin_HB = (Generate.gender? truncate(this.nextFloat(14.0,17.5)) : truncate(this.nextFloat(12.3,15.3)));
        record.add(Hemoglobin_HB+"");

        double HCT = (Generate.gender? truncate(this.nextFloat(41.5,50.4)) : truncate(this.nextFloat(35.9,44.6)));
        record.add(HCT+"");

        float WBC =  this.nextInt(4500,11000);
        record.add(WBC+"");

        float NEU = this.nextInt(45,75); //percentage
        NEU = (float) ((NEU/100) * WBC);
        record.add(NEU+"");

        int Lymph = this.nextInt(900,4900); //count
        record.add(Lymph+"");

        float Mono = truncate(this.nextFloat(2,8)); //percentage
        Mono = (Mono/100) * WBC;
        record.add(Mono+"");

        float Eos = truncate(this.nextFloat(0.1,5.9)); //percentage
        Eos = (Eos/100) * WBC;
        record.add(Eos+"");

        int Baso = this.nextInt(100,300); //count
        record.add(Baso+"");

        int Plt = this.nextInt(150,450);
        record.add(Plt+"");

        float MPV = truncate(this.nextFloat(7.5,12.0));
        record.add(MPV+"");

        float PlateletDistributionWidth = truncate(this.nextFloat(8.3,56.6));
        record.add(PlateletDistributionWidth + "");

        int sodium = this.nextInt(136,145);
        record.add(sodium+"");

        float Potassium = truncate(this.nextFloat(3.5,5.0));
        record.add(Potassium+"");

        int Chloride = this.nextInt(98,106);
        record.add(Chloride +"");

        int Bicarbonate = this.nextInt(23,28);
        record.add(Bicarbonate+"");

        float SCLevel = (Generate.gender? truncate(this.nextFloat(0.74,1.35)) : truncate(this.nextFloat(0.59,1.04)));
        record.add(SCLevel+"");

        int GFR = this.nextInt(90,120);
        record.add(GFR+"");

        int BUN = this.nextInt(7,20);
        record.add(BUN+"");

        int ACR = (Generate.gender? this.nextInt(1,17) : this.nextInt(1,25));
        record.add(ACR+"");

        int BNP =  this.nextInt(10,100);
        record.add(BNP+"");

        int EF = this.nextInt(55,70); //percentage
        record.add(EF+"");

        int FBS = this.nextInt(60,100); //Fasting blood sugar test
        record.add(FBS+"");

        int RBS = this.nextInt(130,200); //random blood sugar
        record.add(RBS+"");

        float hemoglobin_A1c = truncate(this.nextFloat(2.0,5.7)); //percentage
        record.add(hemoglobin_A1c+"");


        //negative =  0, postive =1
        String RIDTs = "0";
        record.add(RIDTs);

        String RMA = "0";
        record.add(RMA);

        String RT_PCR = "1";
        record.add(RT_PCR);

        String RapidCellCultureTest = "0";
        record.add(RapidCellCultureTest);

        String ViralTissueCellCulture = "0";
        record.add(ViralTissueCellCulture);

        String Immunofluorescence = "0";
        record.add(Immunofluorescence);

        String PCR = "1";
        record.add(PCR);

        String NAATs = "0";
        record.add(NAATs);

        String RapidEbolaAntigenTests = "1";
        record.add(RapidEbolaAntigenTests);

        String LaboratoryAntigenTests = "1";
        record.add(LaboratoryAntigenTests);

        String EbolaAntibodyTesting = "1";
        record.add(EbolaAntibodyTesting);

        String LP = "0";
        record.add(LP);

        String Biopsy = "0";
        record.add(Biopsy);

        int XRayProp = nextInt(1,10);
        if(XRayProp > 5)
            record.add("1");
        else
            record.add("0");

        record.add("1"); //ct-scan

        String PET_CTScan = "0";
        record.add(PET_CTScan);

        String Bronchoscopy = "0";
        record.add(Bronchoscopy);

        int ebolaprob = nextInt(0,2);

        int WL = this.nextInt(3,6); //weight lose or poor appetite
        record.add(WL+"");

        int Breathlessness = this.nextInt(0,2);
        record.add(Breathlessness+"");

        int Fatigue = this.nextInt(2,6);
        record.add(Fatigue+"");

        int SwollenAnklesAndLegs = 0;
        record.add(SwollenAnklesAndLegs+"");

        int ItchySkin = 0;
        record.add(ItchySkin+"");

        int Fever = this.nextInt(2,6);
        record.add(Fever+"");

        int Tiredness = this.nextInt(1,6);
        record.add(Tiredness+"");

        int Cough = 0;
        record.add(Cough+"");

        int RunnyNose = 0;
        record.add(RunnyNose+"");

        int Hunger = 0;
        record.add(Hunger+"");

        int ExcessiveThirst = 0;
        record.add(ExcessiveThirst+"");

        int FrequentUrination = 0;
        record.add(FrequentUrination+"");

        int DryMouth = 0;
        record.add(DryMouth+"");

        int BlurredVision = 0;
        record.add(BlurredVision+"");

        int SoreThroat = (ebolaprob == 1? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(SoreThroat+"");

        int HeadAche = (ebolaprob == 1? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(HeadAche+"");

        int BodyAches = (ebolaprob == 1? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(BodyAches+"");

        int Insonmia = 0;
        record.add(Insonmia+"");

        int Nausea = 0; //this.nextInt(0,0);
        record.add(Nausea+"");

        int Vomitting = (ebolaprob == 1? this.nextInt(4,6) : this.nextInt(1,4));;
        record.add(Vomitting+"");

        int MemoryProblems = 0;
        record.add(MemoryProblems+"");

        int Drowsiness = 0;
        record.add(Drowsiness+"");

        int Diarrhoea = (ebolaprob >5? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(Diarrhoea+"");

        String BloodCouphing = "0";
        record.add(BloodCouphing);

        String BloodUrine = "1";
        record.add(BloodUrine);

        int Rash = (ebolaprob >5? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(Rash+"");

        int Chills = (ebolaprob >5? this.nextInt(4,6) : this.nextInt(1,4));
        record.add(Chills+"");

        int StuffyNose = 0;
        record.add(StuffyNose+"");

        String Conjunctivitis = "0";
        record.add(Conjunctivitis);

        String RedEyes = "1";
        record.add(RedEyes);

        String Confusion = "1";
        record.add(Confusion);

        String LosOfTaste = "0";
        record.add(LosOfTaste);

        String className = "5";
        record.add(className);

        //add the record to the rest of the records
        allRecords.add(record);

    }

    /**
     * generate random integers
     * @param lower
     * @param upper
     * @return
     */
    public int nextInt(int lower, int upper){
        SplittableRandom splittableRandom = new SplittableRandom();
        return splittableRandom.nextInt(lower,upper);
    }

    public double nextFloat(double lower, double upper){
        SplittableRandom splittableRandom = new SplittableRandom();
        return splittableRandom.nextDouble(lower,upper);
    }

    private float truncate(double number){

        int integer = (int)number*100;
        float floatNumber = (float) integer;
        return floatNumber/100;
    }
}

