package dk.itu.garbageapp;

public class GarbageCategories {

    private String plastic = "Plastic (Plast)";
    private String bio = "Bio waste (Madaffald)";
    private String bulk = "Bulky waste (Storskrald)";
    private String cardboard = "Cardboard (Pap)";
    private String electro = "Electronic waste (Elektronisk)";
    private String garden = "Garden waste (Haveaffald)";
    private String glass = "Glass (Glas)";
    private String hazard = "Hazardous waste (Farlight affald)";
    private String metal = "Metal";
    private String paper = "Paper (Papir)";
    private String residual = "Residual waste (Restaffald)";

    /**
     * GarbageCategories is a dictionary of the different waste types as defined by the Copenhagen Commune.
     * Use getter methods for correct category descriptions instead of hand-coding.
     */
    GarbageCategories(){
    }

    public String getPlastic() {
        return plastic;
    }

    public String getBio() {
        return bio;
    }

    public String getBulk() {
        return bulk;
    }

    public String getCardboard() {
        return cardboard;
    }

    public String getElectro() {
        return electro;
    }

    public String getGarden() {
        return garden;
    }

    public String getGlass() {
        return glass;
    }

    public String getHazard() {
        return hazard;
    }

    public String getMetal() {
        return metal;
    }

    public String getPaper() {
        return paper;
    }

    public String getResidual() {
        return residual;
    }

}


