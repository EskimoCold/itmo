package web.itmo.lab2.models;

import java.io.Serializable;
import java.util.ArrayList;

public class HitDataTableBean implements Serializable {
    private ArrayList<HitDataBean> hits = new ArrayList<>();

    public HitDataTableBean() {

    }

    public ArrayList<HitDataBean> getHits () {
        return this.hits;
    }

    public void setHits(ArrayList<HitDataBean> hits) {
        this.hits = hits;
    }
}