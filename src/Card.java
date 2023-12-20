public class Card {

    private String ranks;
    private String suits;


    private int suits_index;
    private int ranks_index;

    public Card(String suits,int suits_index,String ranks,int ranks_index) {
        this.suits = suits;
        this.ranks = ranks;
        this.suits_index = suits_index;
        this.ranks_index=ranks_index;
    }

    @Override
    public String toString(){
        return suits + ranks;
    }

    public String getSuits() {
        return suits;
    }

    public String getRanks() {
        return ranks;
    }

    public int getSuits_index() {
        return suits_index;
    }

    public int getRanks_index() {
        return ranks_index;
    }
}
