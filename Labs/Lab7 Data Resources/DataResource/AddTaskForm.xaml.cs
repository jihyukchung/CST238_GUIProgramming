using System;
using System.Windows.Controls;

namespace DataResource
{
    public partial class AddTaskForm : UserControl
    {
        public AddTaskForm()
        {
            InitializeComponent();
        }

        // Remove Help Texts When Clicked
        private void TextBlock_OnClick(object sender, EventArgs e)
        {
            var button = sender as TextBlock;
            button.Text = "";
        }

        // The Add Button
        public event EventHandler AddButton_StatusUpdate;
        private void AddButton_OnClick(object sender, EventArgs e)
        {
            var button = sender as Button;
            if (AddButton_StatusUpdate != null)
            {
                AddButton_StatusUpdate(sender, e);
            }
        }

        // Reset Help Texts
        public void ResetTexts()
        {
            m_taskName.Text = (String)FindResource("ATF_TaskName");
            m_progressSize.Text = (String)FindResource("ATF_ProgressSize");
        }
    }
}
