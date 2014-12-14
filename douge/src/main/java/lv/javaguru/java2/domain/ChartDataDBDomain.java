package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 14.12.2014.
 */
public class ChartDataDBDomain extends DBDomain {


        private Object[] titles;
        private Object[] datas;

    @Override
    protected void buildFullInfoList() {
       fillfullInfoData();
    }


    private void fillfullInfoData(){
        fullInfoList.clear();
        for (int i = 0; i < datas.length ; i++) {
            DBDomainDataInfo dataInfo = new DBDomainDataInfo(titles[i].toString(), datas[i]);
            fullInfoList.put(titles[i].toString(), dataInfo);

        }
    }

    public ChartDataDBDomain(Object[] titles, Object[] datas){
        super();
        this.titles = titles;
        this.datas = datas;
    }

}
