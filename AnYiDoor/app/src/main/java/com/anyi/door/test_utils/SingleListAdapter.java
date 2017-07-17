package com.anyi.door.test_utils;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.anyi.door.R;

import java.util.ArrayList;

/**
 * 单选
 */
public class SingleListAdapter extends BaseAdapter
{

    LayoutInflater inflater;

    public ArrayList<Model> getList()
    {
        return list;
    }

    ArrayList<Model> list;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

    public SingleListAdapter(Context context, ArrayList<Model> data)
    {
        inflater = LayoutInflater.from(context);
        this.list = data;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Model getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        ViewHolder holder = null;

        if (v == null)
        {
            v = inflater.inflate(R.layout.item_judge, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
            holder.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {

                public void onCheckedChanged(RadioGroup group,
                                             int checkedId)
                {
                    Integer pos = (Integer) group.getTag();
                    Model element = list.get(pos);
                    switch (checkedId)
                    { // set the Model to hold the
                        // answer the user picked
                        case R.id.answer0:
                            element.current = Model.ANSWER_ONE_SELECTED;
                            break;
                        case R.id.answer1:
                            element.current = Model.ANSWER_TWO_SELECTED;
                            break;
                        default:
                            element.current = Model.NONE; // Something was
                            // wrong set to
                            // the default
                    }

                }
            });
        }
        else
        {
            holder = (ViewHolder) v.getTag();
        }
        holder.group.setTag(new Integer(position)); // I passed the current position as a tag

        holder.t.setText(list.get(position).question); // Set the question body

        if (list.get(position).current != Model.NONE)
        {
            RadioButton r = (RadioButton) holder.group.getChildAt(list.get(position).current);
            r.setChecked(true);
        }
        else
        {
            holder.group.clearCheck(); // This is required because although the
            // Model could have the current
            // position to NONE you could be dealing
            // with a previous row where
            // the user already picked an answer.

        }
        return v;
    }

    class ViewHolder
    {
        TextView t = null;

        RadioGroup group;

        ViewHolder(View v)
        {
            t = (TextView) v.findViewById(R.id.textView1);
            group = (RadioGroup) v.findViewById(R.id.group_me);
        }

    }

}
