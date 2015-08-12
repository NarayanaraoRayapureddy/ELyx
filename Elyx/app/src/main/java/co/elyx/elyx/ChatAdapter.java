package co.elyx.elyx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.CaseViewHolder> {


    private List<Case> cases;
    public static class CaseViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView doctorName;
        TextView timeStamp;
        TextView symptoms;
        TextView characteristics;
        ImageView profilePic;


        CaseViewHolder(View itemView) {

            super(itemView);
            doctorName = (TextView) itemView.findViewById(R.id.doctor_name);
            timeStamp = (TextView) itemView.findViewById(R.id.datestamp);
            symptoms = (TextView) itemView.findViewById(R.id.symptoms);
            characteristics = (TextView) itemView.findViewById(R.id.lifestyle);
            profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
        }
    }

    public ChatAdapter(List<Case> cases) {
        this.cases = cases;

    }

    @Override
    public CaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item, viewGroup,false);
        CaseViewHolder vh = new CaseViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(CaseViewHolder caseViewHolder, int i) {
        caseViewHolder.doctorName.setText((CharSequence) cases.get(i).doctor);
        caseViewHolder.timeStamp.setText((CharSequence) cases.get(i).time);
        caseViewHolder.symptoms.setText((CharSequence) cases.get(i).symptoms);
        caseViewHolder.characteristics.setText((CharSequence) cases.get(i).characteristics);
        caseViewHolder.profilePic.setImageResource(cases.get(i).doctorPic);
    }


    @Override
    public int getItemCount() {
        return cases.size();
    }

    static class Case{

        String doctor;
        String time;
        String symptoms;
        String characteristics;
        int doctorPic;

        Case(String doctor, String time, String symptoms, String characteristics, int doctorPic) {
            this.doctor = doctor;
            this.time = time;
            this.symptoms = symptoms;
            this.characteristics = characteristics;
            this.doctorPic = doctorPic;


        }

    }
}