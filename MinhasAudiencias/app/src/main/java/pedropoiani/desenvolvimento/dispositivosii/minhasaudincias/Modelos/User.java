package pedropoiani.desenvolvimento.dispositivosii.minhasaudincias.Modelos;


public class User {

    public String username;
    public String nOab;


    public User() {


    }

    public User(String username, String nOab) {

        this.username = username;
        this.nOab = nOab;


    }

    public String getUsername() {
        return username;

    }


    public void setUsername(String username) {
        this.username = username;

    }


    public String getnOab() {
        return nOab;

    }

    public void setnOab(String nOab) {
        this.nOab = nOab;
    }


}
