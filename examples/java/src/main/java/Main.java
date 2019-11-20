
public class Main {

    public static void main(String[] args) {
        NumerApi napi = new NumerApi("<my_public_id>", "<my_secret_key>");

        // download current dataset
        napi.downloadDataset("data.zip");

        // upload predictions
        String submissionId = napi.uploadSubmission("my_predictions.csv")
        System.out.println("submission_id: " + submissionId)

        // get some information from a user's public profile
        System.out.println(napi.getPublicUserProfile("uuazed"));
        }

}
