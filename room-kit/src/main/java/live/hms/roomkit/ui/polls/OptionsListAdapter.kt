package live.hms.roomkit.ui.polls

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import live.hms.roomkit.databinding.LayoutPollQuizOptionsItemBinding
import live.hms.roomkit.databinding.LayoutPollQuizOptionsItemMultiChoiceBinding
import java.util.*

data class Option(var text : String,
                  val showCheckbox : Boolean,
                  var isChecked : Boolean = false, val id : String = UUID.randomUUID().toString())

class OptionsListAdapter : ListAdapter<Option, OptionViewHolder>(DIFFUTIL_CALLBACK) {

    companion object {
        val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Option>() {
            override fun areItemsTheSame(oldItem: Option, newItem: Option): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Option, newItem: Option): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val binding = LayoutPollQuizOptionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OptionViewHolder(binding,::getItem, ::selectOnlyCurrentOption)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(getItem(position))
        // Put the cursor in the edittext as it's created.
        holder.binding.text.requestFocus()
    }

    private fun selectOnlyCurrentOption(position: Int) {
        for ( i in 0 until currentList.size) {
            Log.d("OptionsListAdapter", "Considering ${getItem(i)}, going to be ${i == position}")
            getItem(i).isChecked = i == position
        }
    }
}