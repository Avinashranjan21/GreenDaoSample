package com.example;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class AviDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1000, "com.decimal.kotak");
        addNote(schema);
        new DaoGenerator().generateAll(schema, "/home/decimal/greendao_class");
    }

    private static void addNote(Schema schema) {
        Entity audit_lead_master = schema.addEntity("audit_lead_master");
        audit_lead_master.addStringProperty("lead_title");
        audit_lead_master.addStringProperty("lead_fname");
        audit_lead_master.addStringProperty("lead_lname");
        audit_lead_master.addStringProperty("lead_mother_name");
        audit_lead_master.addStringProperty("lead_religion");
        audit_lead_master.addStringProperty("lead_marital_status");
        audit_lead_master.addStringProperty("lead_mobile");
        audit_lead_master.addStringProperty("lead_altno");
        audit_lead_master.addStringProperty("lead_dispid");
        audit_lead_master.addStringProperty("lead_dob");
        audit_lead_master.addStringProperty("lead_emailid");
        audit_lead_master.addStringProperty("next_appointment_date");
        audit_lead_master.addStringProperty("next_appointment_time");
        audit_lead_master.addStringProperty("active_money");
        audit_lead_master.addStringProperty("investment_account");
        audit_lead_master.addStringProperty("reimbursement_account");
        audit_lead_master.addStringProperty("non_lob_lead");
        audit_lead_master.addStringProperty("remarks");
        audit_lead_master.addStringProperty("lead_current_owner");
        audit_lead_master.addStringProperty("lead_last_activityid");
        audit_lead_master.addStringProperty("lead_last_activitydate");
        audit_lead_master.addStringProperty("lead_dedupe_status");
        audit_lead_master.addStringProperty("lead_status");
        audit_lead_master.addStringProperty("lead_stage");
        audit_lead_master.addStringProperty("salary");
        audit_lead_master.addStringProperty("lead_priority");
        audit_lead_master.addStringProperty("lead_onboard");
        audit_lead_master.addStringProperty("creation_date");
        audit_lead_master.addStringProperty("creation_by");
        audit_lead_master.addStringProperty("modified_date");
        audit_lead_master.addStringProperty("insta_crn_no");
        audit_lead_master.addStringProperty("modified_by");
        audit_lead_master.addStringProperty("insta_accno");
        audit_lead_master.addStringProperty("insta_kit_valid_flag");
        audit_lead_master.addStringProperty("business_disposition");
        audit_lead_master.addStringProperty("kit_type");
        audit_lead_master.addStringProperty("pgn_crn_no");
        audit_lead_master.addStringProperty("pgn_acc_no");
        audit_lead_master.addStringProperty("post_crn_no");
        audit_lead_master.addStringProperty("post_acc_no");
        audit_lead_master.addStringProperty("final_crn_no");
        audit_lead_master.addStringProperty("final_acc_no");
        audit_lead_master.addStringProperty("system_disposition");
        audit_lead_master.addStringProperty("is_sent_sms");
        audit_lead_master.addStringProperty("rec_sync_status");
        audit_lead_master.addStringProperty("rec_sync_timestamp");
        audit_lead_master.addStringProperty("lead_error");
        audit_lead_master.addStringProperty("timestamp");
    }

}
