package GameLogic.InfoSet;

import java.util.ArrayList;

public class InfoSet {

    private ArrayList<Info> infoList;

    public InfoSet() {
        this.infoList = new ArrayList<>();
    }

    public ArrayList<Info> getInfoList() {
        return infoList;
    }

    public void addInfo(Info info) {
        this.infoList.add(info);
    }
}
