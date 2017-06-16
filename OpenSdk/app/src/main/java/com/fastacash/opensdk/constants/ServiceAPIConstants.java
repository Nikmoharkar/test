package com.fastacash.opensdk.constants;

/**
 * Created by nikhil on 10/21/2015.
 */
public interface ServiceAPIConstants {
    public int CREATE_LINK = 0;
    public int UPDATE_LINK = 1;
    public int GET_LINK = 2;

    //different method types for api services.
    public byte GET = 10;
    public byte POST = 11;
    public byte PUT = 12;
    public byte DELETE = 13;

    //link request purpose constants
    public String LINK_PURPOSE_FAST_A_SHARE = "fasta_share";
    public String LINK_PURPOSE_FAST_A_SHOT = "fasta_shot";
    public String LINK_PURPOSE_FAST_A_SCREEN_SHOT = "fasta_screen_shot";
    public String LINK_PURPOSE_FAST_A_SHORT = "fasta_short";
    public String LINK_PURPOSE_FAST_A_SSHORT = "fasta_sshort";

    public String FAST_A_LINK = "https://fasta.link/";

}
