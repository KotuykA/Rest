package rest.json;

public class TrackRequest {

    int id;


    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Track [group=" + id + "]";
    }
}
