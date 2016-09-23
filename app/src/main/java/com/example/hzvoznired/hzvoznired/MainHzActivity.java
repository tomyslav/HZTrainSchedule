package com.example.hzvoznired.hzvoznired;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hzvoznired.hzvoznired.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.TreeMap;



public class MainHzActivity extends Activity {
    private TreeMap<String, String> allHzCities;
    private Spinner hzSpinner;
    static ArrayList<String> itemList;
    private String[] cityData;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hz);
        allHzCities = new TreeMap<String, String>();
        hzSpinner=(Spinner) findViewById(R.id.hz_city_list_spinner);
        //populating allHzCities
        populateTreeMap();
        //setting spinner with allHzCities data
        setHzSpinnerValues(allHzCities);

        hzSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                cityData = getCityCode(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dh = DatabaseHelper.getInstance(this);
        dh.printAllDbEntries();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_hz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



//http://blog.mikeclassic.ca/post/android-populating-spinner-with-strings-and-id
    public void setHzSpinnerValues(TreeMap<String, String> h){
        itemList = new ArrayList<String>();
        for (String key : allHzCities.keySet()) {
            itemList.add(key);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hzSpinner.setAdapter(spinnerAdapter);
    }



    public void saveMethod(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(MainHzActivity.this).create();
        alertDialog.setTitle("oups!!!");
        alertDialog.setMessage("This is not implemented yet");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void checkMethod(View view){
        Intent intent = new Intent(this, DirectionActivity.class);
        intent.putExtra("cityData",cityData);
        MainHzActivity.this.startActivity(intent);
    }


    public String[] getCityCode(int p){
        String i = hzSpinner.getItemAtPosition(p).toString();
        String[] data={i.toString(),allHzCities.get(i.toString())};
        return data;
    }




    public void populateTreeMap(){
        allHzCities.put("ANDRIJAŠEVCI","214");
        allHzCities.put("ANDRIJEVCI","1");
        allHzCities.put("ANTUNOVAC","215");
        allHzCities.put("BADLJEVINA","216");
        allHzCities.put("BAKOVIĆI","217");
        allHzCities.put("BANOVA JARUGA","2");
        allHzCities.put("BANJA","218");
        allHzCities.put("BEDEKOVČINA","3");
        allHzCities.put("BELAVIĆI","219");
        allHzCities.put("BELI MANASTIR","4");
        allHzCities.put("BENKOVAC","5");
        allHzCities.put("BIBINJE","6");
        allHzCities.put("BIJELA","486");
        allHzCities.put("BIJELO BRDO","220");
        allHzCities.put("BIZOVAC","7");
        allHzCities.put("BJELOVAR","8");
        allHzCities.put("BLACKO JAKŠIĆ","9");
        allHzCities.put("BLATA","10");
        allHzCities.put("BLINJSKI KUT","11");
        allHzCities.put("BOROVO-TRPINJA","487");
        allHzCities.put("BORUT","12");
        allHzCities.put("BOTOVO","488");
        allHzCities.put("BOŽJAKOVINA","221");
        allHzCities.put("BRDAŠCE","222");
        allHzCities.put("BRDOVEC","223");
        allHzCities.put("BRĐANI KRAJIŠKI","489");
        allHzCities.put("BREGI","13");
        allHzCities.put("BREZINE BUJAVICA","224");
        allHzCities.put("BREZOVLJANI","225");
        allHzCities.put("BRGUD","501");
        allHzCities.put("BRIJEST","203");
        allHzCities.put("BRLOG GRAD","226");
        allHzCities.put("BROD MORAVICE","14");
        allHzCities.put("BRODSKI STUPNIK","227");
        allHzCities.put("BRŠADIN","228");
        allHzCities.put("BRŠADIN LIPOVAČA","229");
        allHzCities.put("BUBNJARCI","230");
        allHzCities.put("BUČJE-KOPRIVNICA","231");
        allHzCities.put("BUDINŠĆINA","15");
        allHzCities.put("BUDROVCI","232");
        allHzCities.put("BULIĆ","233");
        allHzCities.put("BUZET","16");
        allHzCities.put("BUZIN","234");
        allHzCities.put("CABUNA","17");
        allHzCities.put("CAREVDAR","235");
        allHzCities.put("CERA","236");
        allHzCities.put("CERJE TUŽNO","18");
        allHzCities.put("CERNA","19");
        allHzCities.put("CEROVLJANI","237");
        allHzCities.put("CEROVLJE","20");
        allHzCities.put("CIGLENIK","238");
        allHzCities.put("CIRKVENA","239");
        allHzCities.put("CRET","240");
        allHzCities.put("ČABRUNIĆI","241");
        allHzCities.put("ČABRUNIĆI SELO","242");
        allHzCities.put("ČAČINCI","21");
        allHzCities.put("ČAGLIN","22");
        allHzCities.put("ČAKOVEC","23");
        allHzCities.put("ČAKOVEC-BUZOVEC","243");
        allHzCities.put("ČEHOVEC","244");
        allHzCities.put("ČEMINAC","245");
        allHzCities.put("ČEPIN","246");
        allHzCities.put("ČUKOVEC","247");
        allHzCities.put("ČULINEC","212");
        allHzCities.put("DABAR","248");
        allHzCities.put("DALMATIN. OSTROVICA","249");
        allHzCities.put("DALJ","24");
        allHzCities.put("DARDA","25");
        allHzCities.put("DARUVAR","26");
        allHzCities.put("DEANOVEC","27");
        allHzCities.put("DEBELJAK","250");
        allHzCities.put("DELNICE","28");
        allHzCities.put("DESINEC","251");
        allHzCities.put("DOBROVAC","252");
        allHzCities.put("DOLIĆE","253");
        allHzCities.put("DOMAGOVIĆ","254");
        allHzCities.put("DONJA STUBICA","204");
        allHzCities.put("DONJA VRBA","255");
        allHzCities.put("DONJA VRIJESKA","256");
        allHzCities.put("DONJE DUBRAVE","257");
        allHzCities.put("DONJI DOLAC","258");
        allHzCities.put("DONJI KRALJEVEC","29");
        allHzCities.put("DONJI LIPOVEC","259");
        allHzCities.put("DONJI MIHALJEVEC","260");
        allHzCities.put("DOPSIN","261");
        allHzCities.put("DRAGALIĆ","262");
        allHzCities.put("DRAGANIĆI","30");
        allHzCities.put("DRAGOVCI","263");
        allHzCities.put("DRENOVCI","31");
        allHzCities.put("DRIVENIK","32");
        allHzCities.put("DRNIŠ","33");
        allHzCities.put("DRNJE","490");
        allHzCities.put("DUBRAVA ZABOČKA","264");
        allHzCities.put("DUGA RESA","34");
        allHzCities.put("DUGO SELO","35");
        allHzCities.put("DUKOVEC","265");
        allHzCities.put("DUNJKOVEC","266");
        allHzCities.put("ĐAKOVO","36");
        allHzCities.put("ĐEVRSKE","268");
        allHzCities.put("ĐULOVAC","37");
        allHzCities.put("ĐURĐENOVAC","38");
        allHzCities.put("ĐURĐEVAC","39");
        allHzCities.put("ĐURMANEC","40");
        allHzCities.put("ERDUT","41");
        allHzCities.put("ERNESTINOVO","269");
        allHzCities.put("FERIČANCI","270");
        allHzCities.put("FRIGIS","271");
        allHzCities.put("FUŽINE","42");
        allHzCities.put("GABOS","272");
        allHzCities.put("GAJNICE","273");
        allHzCities.put("GALIŽANA","274");
        allHzCities.put("GALOVCI","275");
        allHzCities.put("GARČIN","43");
        allHzCities.put("GENERALSKI STOL","44");
        allHzCities.put("GOLUBOVEC","45");
        allHzCities.put("GOMIRJE","46");
        allHzCities.put("GORNJA STUBICA","47");
        allHzCities.put("GORNJE DUBRAVE","48");
        allHzCities.put("GORNJI ZVEČAJ","276");
        allHzCities.put("GOSPIĆ","49");
        allHzCities.put("GRABOŠTANI","277");
        allHzCities.put("GRAČAC","50");
        allHzCities.put("GRADEC","278");
        allHzCities.put("GRADIŠTE","279");
        allHzCities.put("GREDA","51");
        allHzCities.put("GRGINAC","280");
        allHzCities.put("GRGINAC NOVI","281");
        allHzCities.put("GUNJA","282");
        allHzCities.put("HARMICA","283");
        allHzCities.put("HEKI","284");
        allHzCities.put("HEKI TOVARIŠTE","205");
        allHzCities.put("HORVATI","52");
        allHzCities.put("HRASTOVAC","53");
        allHzCities.put("Hrastovac-Vučki","467");
        allHzCities.put("HRAŠĆINA-TRGOVIŠĆE","285");
        allHzCities.put("HROMEC","286");
        allHzCities.put("HRSOVO","287");
        allHzCities.put("HRVATSKA DUBICA","54");
        allHzCities.put("HRVATSKA KOSTAJNICA","288");
        allHzCities.put("HRVATSKI LESKOVAC","55");
        allHzCities.put("HUM LUG","213");
        allHzCities.put("HUM U ISTRI","289");
        allHzCities.put("ILAČA","290");
        allHzCities.put("ILOVA","291");
        allHzCities.put("IVANEC","56");
        allHzCities.put("IVANIĆ GRAD","57");
        allHzCities.put("IVANKOVO","58");
        allHzCities.put("IVOŠEVCI","502");
        allHzCities.put("JALŽABET","59");
        allHzCities.put("JASENOVAC","206");
        allHzCities.put("JASTREBARSKO","60");
        allHzCities.put("JELISAVAC","292");
        allHzCities.put("JOSIPDOL","61");
        allHzCities.put("JOSIPOVAC","62");
        allHzCities.put("JURDANI","491");
        allHzCities.put("JURŠIĆI","293");
        allHzCities.put("JUŠIĆI","492");
        allHzCities.put("KALDRMA","294");
        allHzCities.put("KALINOVAC","295");
        allHzCities.put("KAMANJE","63");
        allHzCities.put("KANFANAR","64");
        allHzCities.put("KARLOVAC","65");
        allHzCities.put("KARLOVAC-Centar","296");
        allHzCities.put("KAŠTEL GOMILICA","297");
        allHzCities.put("KAŠTEL KAMBELOVAC","298");
        allHzCities.put("KAŠTEL STARI","66");
        allHzCities.put("KAŠTEL SUĆURAC","67");
        allHzCities.put("KISTANJE","68");
        allHzCities.put("KLOKOČEVAC","299");
        allHzCities.put("KLOŠTAR","69");
        allHzCities.put("KNEŽCI","300");
        allHzCities.put("KNIN","70");
        allHzCities.put("KOMIN","301");
        allHzCities.put("KONJŠĆINA","71");
        allHzCities.put("KOPANICA-BERAVCI","72");
        allHzCities.put("KOPRIVNICA","73");
        allHzCities.put("KOPRNO","302");
        allHzCities.put("KORENIČANI","303");
        allHzCities.put("KOSOVO","74");
        allHzCities.put("KOŠARE","304");
        allHzCities.put("KOŠKA","75");
        allHzCities.put("KOTORIBA","76");
        allHzCities.put("KOŽLOVAC","305");
        allHzCities.put("KRAJCAR BRIJEG","306");
        allHzCities.put("KRAPINA","77");
        allHzCities.put("KRIŽEVCI","78");
        allHzCities.put("KRNJEVO","493");
        allHzCities.put("KRUŠLJEVEC","307");
        allHzCities.put("KRVAVAC","308");
        allHzCities.put("KUKAČA","79");
        allHzCities.put("KUKUNJEVAC","309");
        allHzCities.put("KULA NORINSKA","310");
        allHzCities.put("KULJEVČICA","311");
        allHzCities.put("KUNOVEC SUBOTICA","312");
        allHzCities.put("KUPJAK","313");
        allHzCities.put("KUPLJENOVO","314");
        allHzCities.put("KUSTOŠIJA","315");
        allHzCities.put("KUTI","316");
        allHzCities.put("KUTINA","80");
        allHzCities.put("LABIN DALMATINSKI","81");
        allHzCities.put("LADUČ","317");
        allHzCities.put("LASLOVO-KORODJ","318");
        allHzCities.put("LATIN","319");
        allHzCities.put("LATINOVAC","320");
        allHzCities.put("LAZINA","321");
        allHzCities.put("LEKENIK","82");
        allHzCities.put("LEPAVINA","83");
        allHzCities.put("LEPOGLAVA","84");
        allHzCities.put("LEPURI","322");
        allHzCities.put("LIČ","323");
        allHzCities.put("LIČKA JESENICA","85");
        allHzCities.put("LIČKI PODHUM","324");
        allHzCities.put("LIČKO LEŠĆE","86");
        allHzCities.put("LIPIK","87");
        allHzCities.put("LIPOVAC-KORITNA","325");
        allHzCities.put("LIPOVLJANI","88");
        allHzCities.put("LOKVE","89");
        allHzCities.put("LONDŽICA","326");
        allHzCities.put("LOVINAC","90");
        allHzCities.put("LUDBREG","91");
        allHzCities.put("LUDINA","92");
        allHzCities.put("LUKA","93");
        allHzCities.put("LUPOGLAV","94");
        allHzCities.put("LUŽANI-MALINO","327");
        allHzCities.put("LJESKOVICA","328");
        allHzCities.put("LJUBOŠINA","329");
        allHzCities.put("MACINEC","330");
        allHzCities.put("MAĐAREVO","331");
        allHzCities.put("MAHIČNO","95");
        allHzCities.put("MAJUR","96");
        allHzCities.put("MAJUREC","332");
        allHzCities.put("MAKSIMIR","333");
        allHzCities.put("MALA SUBOTICA","97");
        allHzCities.put("MANDALINA","334");
        allHzCities.put("MARKUSICA-ANTIN","336");
        allHzCities.put("MARTIJANEC","337");
        allHzCities.put("MASLENJAČA","98");
        allHzCities.put("MAVRAČIĆI","338");
        allHzCities.put("MEDAK","494");
        allHzCities.put("MEĐURIĆ","339");
        allHzCities.put("MEJA","99");
        allHzCities.put("MELNICE","340");
        allHzCities.put("METKOVIĆ","100");
        allHzCities.put("MIHALJEVCI","341");
        allHzCities.put("MIKLEUŠ","342");
        allHzCities.put("MIRKOVCI","343");
        allHzCities.put("MIŠULINOVAC","344");
        allHzCities.put("MORAVICE","101");
        allHzCities.put("MOSLAVAČKA GRAČENICA","102");
        allHzCities.put("MRACLIN","345");
        allHzCities.put("MRZLO POLJE","103");
        allHzCities.put("MUČNA REKA","104");
        allHzCities.put("MURSKO SREDIŠĆE","207");
        allHzCities.put("NADIN","346");
        allHzCities.put("NAŠICE","105");
        allHzCities.put("NAŠICE GRAD","347");
        allHzCities.put("NAŠIČKA BREZNICA","348");
        allHzCities.put("NIZA","349");
        allHzCities.put("NORMANCI","350");
        allHzCities.put("NOVA BUKOVICA","  351");
        allHzCities.put("NOVA GRADIŠKA","106");
        allHzCities.put("NOVA KAPELA-BATRINA","107");
        allHzCities.put("NOVAKI","352");
        allHzCities.put("NOVAKOVEC","353");
        allHzCities.put("NOVI DALJ","495");
        allHzCities.put("NOVI DVORI","108");
        allHzCities.put("NOVI MAROF","109");
        allHzCities.put("NOVIGRAD PODRAVSKI","355");
        allHzCities.put("NOVO SELO ROK","356");
        allHzCities.put("NOVOSELCI","357");
        allHzCities.put("NOVOSELEC","110");
        allHzCities.put("NOVSKA","111");
        allHzCities.put("NUGLA","358");
        allHzCities.put("NUŠTAR STAJALIŠTE","359");
        allHzCities.put("OĆESTOVO","496");
        allHzCities.put("ODRA","360");
        allHzCities.put("OGULIN","112");
        allHzCities.put("OGULINSKI HRELJIN","113");
        allHzCities.put("OKUČANI","114");
        allHzCities.put("OPATIJA-MATULJI","115");
        allHzCities.put("OPUZEN","116");
        allHzCities.put("ORIOVAC","117");
        allHzCities.put("OROLIK","361");
        allHzCities.put("OROSLAVJE","208");
        allHzCities.put("OSIJEK","118");
        allHzCities.put("OSIJEK DONJI GRAD","119");
        allHzCities.put("OSIJEK DRAVSKI MOST","362");
        allHzCities.put("OSIJEK LUKA","363");
        allHzCities.put("OSIJEK OLT","364");
        allHzCities.put("OSTRNA","365");
        allHzCities.put("OSTROVO","366");
        allHzCities.put("OŠTARIJE","120");
        allHzCities.put("OŠTARIJE RAVNICE","367");
        allHzCities.put("OTOK","121");
        allHzCities.put("OZALJ","122");
        allHzCities.put("PAKRAC","209");
        allHzCities.put("PAKRAC GRAD","368");
        allHzCities.put("PAPIĆI","369");
        allHzCities.put("PAULOVAC","370");
        allHzCities.put("PAZIN","123");
        allHzCities.put("PČELIĆ","211");
        allHzCities.put("PEPELANA","371");
        allHzCities.put("PERKOVCI","372");
        allHzCities.put("PERKOVIĆ","124");
        allHzCities.put("PERMANI","497");
        allHzCities.put("PERUŠIĆ","125");
        allHzCities.put("PEŠĆENICA","373");
        allHzCities.put("PETROVE GORE","374");
        allHzCities.put("PITOMAČA","126");
        allHzCities.put("PIVNICA","375");
        allHzCities.put("PLANJANE","376");
        allHzCities.put("PLASE","127");
        allHzCities.put("PLAŠKI","128");
        allHzCities.put("PLAVČA DRAGA","503");
        allHzCities.put("PLAVNO","498");
        allHzCities.put("PLETERNICA","129");
        allHzCities.put("PLOČE","130");
        allHzCities.put("PODRAVSKA BISTRICA","377");
        allHzCities.put("PODRUTE","202");
        allHzCities.put("PODSUSED STAJALIŠTE","378");
        allHzCities.put("PODSUSED tvornica","499");
        allHzCities.put("POJATNO","379");
        allHzCities.put("POLJANA","380");
        allHzCities.put("POLJANKA","381");
        allHzCities.put("POPOVAČA","131");
        allHzCities.put("POTOČANI-KATINAC","382");
        allHzCities.put("POZNANOVEC","383");
        allHzCities.put("POŽEGA","132");
        allHzCities.put("PREČEC STAJALIŠTE","384");
        allHzCities.put("PRESLO","385");
        allHzCities.put("PRGOMET","386");
        allHzCities.put("PRIMORSKI DOLAC","133");
        allHzCities.put("PRIMORSKI SV. JURAJ","387");
        allHzCities.put("PRIMORSKO VRPOLJE","388");
        allHzCities.put("Pristav-Krapinski-St","389");
        allHzCities.put("PRIVLAKA","134");
        allHzCities.put("PRKOS","390");
        allHzCities.put("PULA","135");
        allHzCities.put("RADUČIĆ","391");
        allHzCities.put("RAJIĆ","392");
        allHzCities.put("RASINJA","136");
        allHzCities.put("RAŠTEVIĆ","393");
        allHzCities.put("RATKOVICA","394");
        allHzCities.put("RAŽINE","137");
        allHzCities.put("REMETINEC","395");
        allHzCities.put("REPINEC","396");
        allHzCities.put("REPUŠNICA","397");
        allHzCities.put("RIJEKA","138");
        allHzCities.put("RIPIŠTE","398");
        allHzCities.put("ROČ","139");
        allHzCities.put("ROČKO POLJE","399");
        allHzCities.put("ROGOTIN","140");
        allHzCities.put("ROKOVCI","400");
        allHzCities.put("ROVIŠĆE","401");
        allHzCities.put("RUDOPOLJE","141");
        allHzCities.put("RUKAVAC","500");
        allHzCities.put("SADINE","402");
        allHzCities.put("SAMATOVCI","403");
        allHzCities.put("SARVAŠ","142");
        allHzCities.put("SAVIČENTA","404");
        allHzCities.put("SAVSKI MAROF","143");
        allHzCities.put("SEDRAMIĆ","405");
        allHzCities.put("SEMELJCI","504");
        allHzCities.put("SESVETE","144");
        allHzCities.put("SESVETSKI KRALJEVEC","406");
        allHzCities.put("SIBINJ","145");
        allHzCities.put("SIKIREVCI","407");
        allHzCities.put("SIRAČ","146");
        allHzCities.put("SIROVA KATALENA","408");
        allHzCities.put("SISAK","147");
        allHzCities.put("SISAK CAPRAG","148");
        allHzCities.put("SIVERIĆ","409");
        allHzCities.put("SKRAD","149");
        allHzCities.put("SLADOJEVCI","410");
        allHzCities.put("SLAKOVCI","411");
        allHzCities.put("SLATINA","150");
        allHzCities.put("SLAVONSKI BROD","151");
        allHzCities.put("SLAVONSKI ŠAMAC","152");
        allHzCities.put("SLOBODNICA","412");
        allHzCities.put("SMOLJANCI","413");
        allHzCities.put("SOKOLOVAC","414");
        allHzCities.put("SOLIN","153");
        allHzCities.put("SPAČVA","154");
        allHzCities.put("SPLIT","155");
        allHzCities.put("SPLIT PREDGRAĐE","156");
        allHzCities.put("SREMSKE LAZE","415");
        allHzCities.put("STABLINA","416");
        allHzCities.put("STANDARD","417");
        allHzCities.put("STARA SUBOCKA","418");
        allHzCities.put("STARE PLAVNICE","419");
        allHzCities.put("STARI MIKANOVCI","   157");
        allHzCities.put("STARI SLATINIK","420");
        allHzCities.put("STARO PETROVO SELO","158");
        allHzCities.put("STARO TOPOLJE","421");
        allHzCities.put("STAZA","422");
        allHzCities.put("STAŽNJEVEC","423");
        allHzCities.put("STRIZIVOJNA VRPOLJE","159");
        allHzCities.put("STUBIČKE TOPLICE","424");
        allHzCities.put("STUPNO","425");
        allHzCities.put("SUHOPOLJE","160");
        allHzCities.put("SUKOŠAN","426");
        allHzCities.put("SULKOVCI","427");
        allHzCities.put("SUNJA","161");
        allHzCities.put("SUŠAK PEĆINE","162");
        allHzCities.put("SUTLA","428");
        allHzCities.put("SV. IVAN ŽABNO","210");
        allHzCities.put("SV. KRIŽ ZAČRETJE","163");
        allHzCities.put("SVETI ILIJA","429");
        allHzCities.put("SVETI PETAR U ŠUMI"," 164");
        allHzCities.put("ŠAPJANE","165");
        allHzCities.put("ŠAŠ","430");
        allHzCities.put("ŠIBENIK","166");
        allHzCities.put("ŠIDSKI BANOVCI","431");
        allHzCities.put("ŠIJANA","432");
        allHzCities.put("ŠIRINEC","433");
        allHzCities.put("ŠKABRNJE","167");
        allHzCities.put("ŠKODINOVAC","434");
        allHzCities.put("ŠKRINJARI","435");
        allHzCities.put("ŠKRLJEVO","168");
        allHzCities.put("ŠOPOT","436");
        allHzCities.put("ŠPIČKOVINA","437");
        allHzCities.put("ŠPIŠIĆ BUKOVICA","169");
        allHzCities.put("ŠTRUCLJEVO","438");
        allHzCities.put("ŠUŠNJEVO SELO","439");
        allHzCities.put("TEPLJUH","440");
        allHzCities.put("TOUNJ","441");
        allHzCities.put("TOVARNIK","170");
        allHzCities.put("TRENKOVO","442");
        allHzCities.put("TRNAVA","443");
        allHzCities.put("TURČIN","171");
        allHzCities.put("TUROPOLJE","172");
        allHzCities.put("UNEŠIĆ","173");
        allHzCities.put("VARAŽDIN","174");
        allHzCities.put("VELIKA","175");
        allHzCities.put("VELIKA GORICA","176");
        allHzCities.put("VELIKA VES","444");
        allHzCities.put("VELIKO TRGOVIŠĆE","177");
        allHzCities.put("VELIKO TROJSTVO","445");
        allHzCities.put("VELIMIROVAC","446");
        allHzCities.put("VIDOVEC","447");
        allHzCities.put("VINKOVAČKI BANOVCI","448");
        allHzCities.put("VINKOVAČKO NOVO SELO","449");
        allHzCities.put("VINKOVCI","178");
        allHzCities.put("VINKOVCI BOLNICA","450");
        allHzCities.put("VIRJE","179");
        allHzCities.put("VIROVITICA","180");
        allHzCities.put("VIROVITICA GRAD","451");
        allHzCities.put("VIŠKOVCI","452");
        allHzCities.put("VIŠNJEVAC","453");
        allHzCities.put("VIŠNJEVAC IPK","454");
        allHzCities.put("VIŠNJICA","455");
        allHzCities.put("VLADISLAVCI","181");
        allHzCities.put("VODNJAN","182");
        allHzCities.put("VODNJAN STAJALIŠTE","456");
        allHzCities.put("VODOVOD","457");
        allHzCities.put("VOĐINCI","458");
        allHzCities.put("VOJAKOVAČKI KLOŠTAR","459");
        allHzCities.put("VOJNOVAC","460");
        allHzCities.put("VOLINJA","183");
        allHzCities.put("VOLODER","461");
        allHzCities.put("VRAPČE","462");
        allHzCities.put("VRATA","463");
        allHzCities.put("VRATIŠINEC","464");
        allHzCities.put("VRBANJA","465");
        allHzCities.put("VRBOVA","466");
        allHzCities.put("VRBOVEC","184");
        allHzCities.put("VRBOVSKO","185");
        allHzCities.put("VRHOVINE","186");
        allHzCities.put("VUJASINOVIĆI","506");
        allHzCities.put("VUKOSAVLJEVICA","468");
        allHzCities.put("VUKOVAR","187");
        allHzCities.put("VUKOVAR-BOROVO NAS.","188");
        allHzCities.put("VUKOVJE","469");
        allHzCities.put("ZABOK","189");
        allHzCities.put("ZADAR","190");
        allHzCities.put("ZADUBRAVLJE","470");
        allHzCities.put("ZAGREB GL. KOL.","191");
        allHzCities.put("ZAGREB KLARA","192");
        allHzCities.put("ZAGREB ZAP. KOL.","193");
        allHzCities.put("ZALESINA","194");
        allHzCities.put("ZALUKA","471");
        allHzCities.put("ZAPOLJE","472");
        allHzCities.put("ZAPREŠIĆ","195");
        allHzCities.put("ZAPREŠIĆ-SAVSKA","473");
        allHzCities.put("ZARILAC","474");
        allHzCities.put("ZBELAVA","475");
        allHzCities.put("ZDENCI-ORAHOVICA","196");
        allHzCities.put("ZDENČINA","197");
        allHzCities.put("ZLATAR-BISTRICA","198");
        allHzCities.put("ZLOBIN","477");
        allHzCities.put("ZOLJAN","478");
        allHzCities.put("ZORKOVAC","479");
        allHzCities.put("ZRMANJA","505");
        allHzCities.put("ZVEČAJ","199");
        allHzCities.put("ŽABJAK","480");
        allHzCities.put("ŽEINCI","481");
        allHzCities.put("ŽITNIĆ","200");
        allHzCities.put("ŽIVAJA","482");
        allHzCities.put("ŽMINJ","483");
        allHzCities.put("ŽRNOVAC","484");
        allHzCities.put("ŽUPANJA","201");
        allHzCities.put("ŽUTNICA","485");
    }



}
