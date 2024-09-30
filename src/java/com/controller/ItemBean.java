package com.controller;

import com.dao.ItemDAO;
import com.mode.pojo.TblItem;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ItemBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<TblItem> norrItems;
    private List<TblItem> extraItems;
    private int gameId;
    private ItemDAO itemDAO = new ItemDAO();

    // This method will be used to load items when gameId is set
public String setGameId(int gameId) {
    this.gameId = gameId;
    loadItems();  // Load items based on gameId

    // Navigate to a specific page based on gameId
    switch (gameId) {
        case 1: return "ff.xhtml"; // navigasi ke freefire.xhtml
        case 2: return "genshin";  // navigasi ke genshin.xhtml
        case 3: return "hsr.xhtml";      // navigasi ke hsr.xhtml
        case 4: return "hok.xhtml"; // navigasi ke honorofkings.xhtml
        case 5: return "ml.xhtml";       // navigasi ke ml.xhtml
        case 6: return "pubg.xhtml";     // navigasi ke pubg.xhtml
        case 7: return "valo.xhtml";     // navigasi ke valo.xhtml
        case 8: return "wuwa.xhtml";     // navigasi ke wuwa.xhtml
        default: return "index.xhtml"; // navigasi ke halaman default
    }
}

    public int getGameId() {
        return gameId;
    }

    public void loadItems() {
        norrItems = itemDAO.getItemsByGameIdAndType(gameId, "Norr");
        extraItems = itemDAO.getItemsByGameIdAndType(gameId, "Extra");
    }

    public List<TblItem> getNorrItems() {
        return norrItems;
    }

    public void setNorrItems(List<TblItem> norrItems) {
        this.norrItems = norrItems;
    }

    public List<TblItem> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(List<TblItem> extraItems) {
        this.extraItems = extraItems;
    }
}
