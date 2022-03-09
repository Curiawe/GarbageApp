package dk.itu.garbageapp;

public class GarbageCategories {

    /**
     * GarbageCategories is a dictionary of the different waste types as defined by the Copenhagen Commune.
     * Use getter methods for correct category descriptions instead of hand-coding.
     */
    GarbageCategories(){
    }

    public String getPlastic() {
        return "Plastic (Plast)";
    }

    public String getBio() {
        return "Bio waste (Madaffald)";
    }

    public String getBulk() {
        return "Bulky waste (Storskrald)";
    }

    public String getCardboard() {
        return "Cardboard (Pap)";
    }

    public String getElectro() {
        return "Electronic waste (Elektronisk)";
    }

    public String getGarden() {
        return "Garden waste (Haveaffald)";
    }

    public String getGlass() {
        return "Glass (Glas)";
    }

    public String getHazard() {
        return "Hazardous waste (Farlight affald)";
    }

    public String getMetal() {
        return "Metal";
    }

    public String getPaper() {
        return "Paper (Papir)";
    }

    public String getResidual() {
        return "Residual waste (Restaffald)";
    }

}


