/************************************************************************
 * File	Name	Lab7_JihyukChung/DataResource
 * Author		Jihyuk Chung
 * Date			May 14, 2018 Tue
 * Modified		May 15, 2018 Wed - Implemented ArrayList and AddTaskForm
 *      		May 16, 2018 Thu - Implemented ListTaskForm
 *      		May 17, 2018 Fri - Bux Fixes
 *				May 19, 2018 Sun - Menu Added
 *				                 - First Version Submited
 *			    May 21, 2018 Tue - Removed List and Added Edit Button
 *			                     - Second Version Submited
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 ***********************************************************************/

using System;
using System.Reflection;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Shell;

namespace DataResource
{
    public partial class MainWindow : Window
    {
        const int DELAY_1SEC = 1000;
        const int DEFAULT_LIST_SIZE = 3;

        private int m_listSize = 0;
        private AddTaskForm m_addForm = new AddTaskForm();
        private ListTaskForm m_listForm = new ListTaskForm();
        private Button m_newButton = new Button();

        private double m_progressMax = 0;
        private double m_progress = 0;

        public MainWindow()
        {
            InitializeComponent();
            BugFix_LeftDropDownMenu();
            DefaultConstruct();
            GetDefaultListItems();
        }

        // To fix the WPF menu displays its popup menu horizontally left oriented and outside the main window
        // Potential cause 1: Windows default Touch/TabletPCs setting is set to left handed
        // Written by: Damon Armstrong
        // Source: www.red-gate.com/simple-talk/blogs/wpf-menu-displays-to-the-left-of-the-window/
        public void BugFix_LeftDropDownMenu()
        {
            var menuDropAlignmentField = typeof(SystemParameters).GetField("_menuDropAlignment", BindingFlags.NonPublic | BindingFlags.Static);
            Action setAlignmentValue = () =>
            {
                if (SystemParameters.MenuDropAlignment && menuDropAlignmentField != null) menuDropAlignmentField.SetValue(null, false);
            };
            setAlignmentValue();
            SystemParameters.StaticPropertyChanged += (sender, e) => { setAlignmentValue(); };
        }

        // Initialize Main UI Items
        private void DefaultConstruct()
        {
            // AddTaskForm & ListTaskForm
            m_addForm.AddButton_StatusUpdate += new EventHandler(AddButton_Response);

            // New Button
            // I tried to avoid using this.FindResource because it gives a weird error (CS1061).
            // However, apparantly Application.Current.Resources and Application.Current.FindResource
            // both can't find "TextStyle"
            m_newButton.Style = this.FindResource("TextStyle") as Style;
            m_newButton.Content = Application.Current.Resources["Main_NewButton"] as String;
            m_newButton.Click += NewButton_Reponse;

            // Add All Items Above
            m_todoPanel.Children.Add(m_newButton);
        }

        // Default List
        private void GetDefaultListItems()
        {
            for (int i = 1; i < DEFAULT_LIST_SIZE + 1; i++)
            {
                Random rnd = new Random();
                String res = "ToDoList_Item" + i.ToString();
                NewButton_Reponse(null, null);
                m_addForm.m_taskName.Text = Application.Current.Resources[res] as String;
                m_addForm.m_progressSize.Text = (i * 5).ToString();
                AddButton_Response(null, null);
            }
        }

        // About Menu
        private void About_ClickReponse(object sender, EventArgs e)
        {
            String msgTextTop = Application.Current.Resources["Menu_AboutText1"] as String + "\n"
                              + Application.Current.Resources["Menu_AboutText2"] as String + "\n\n";
            String msgTextBot = Application.Current.Resources["Menu_AboutText3"] as String + "\n"
                              + Application.Current.Resources["Menu_AboutText4"] as String + "\n"
                              + Application.Current.Resources["Menu_AboutText5"] as String + "\n"
                              + Application.Current.Resources["Menu_AboutText6"] as String + "\n";

            MessageBoxResult about = MessageBox.Show(msgTextTop + msgTextBot, Application.Current.Resources["Menu_About"] as String);
        }

        // Exit Menu
        private void Exit_ClickReponse(object sender, EventArgs e)
        {
            System.Windows.Application.Current.Shutdown();
        }

        // New Button Click Response From MainWindow
        private void NewButton_Reponse(object sender, EventArgs e)
        {
            m_addForm.ResetTexts();
            m_todoPanel.Children.Remove(m_newButton);
            m_todoPanel.Children.Add(m_addForm);
        }

        // Check 'progress size' from AddTaskForm is a number 
        private bool InputValidation()
        {
            bool isAllNumber = true;

            foreach (char character in m_addForm.m_progressSize.Text)
            {
                if (character < '0' || character > '9')
                {
                    isAllNumber = false;
                }
            }

            return isAllNumber;
        }

        // Transsfer items from AddButton (AddTaskForm.xaml) to listTaskForm
        // and add it to the panel
        private void AddButton_Response(object sender, EventArgs e)
        {
            if (InputValidation())
            {
                m_listForm = new ListTaskForm();
                m_listForm.EditButton_StatusUpdate += new EventHandler<ListTaskForm>(EditButton_Response);
                m_listForm.ProgressValue_StatusUpdate += new EventHandler<double>(ChangeProgressValueState);
                m_listForm.ProgressMaximum_StatusUpdate += new Action<bool, double>(ChangeProgressMaximumState);
                
                m_listForm.m_task.Text = m_addForm.m_taskName.Text;
                m_listForm.m_progress.Maximum = Int32.Parse(m_addForm.m_progressSize.Text);
                m_listForm.m_progress.Minimum = 0;
                m_listForm.m_progress.Value = 0;

                m_todoPanel.Children.Remove(m_addForm);
                m_todoPanel.Children.Add(m_listForm);
                m_todoPanel.Children.Add(m_newButton);

                m_listSize++;
            }
            else
            {
                String errorMsgBoxName = Application.Current.Resources["ATF_Error"] as String;
                String errorMsgBoxText = Application.Current.Resources["ATF_ErrorMsg"] as String;
                MessageBoxResult result = MessageBox.Show(errorMsgBoxText, errorMsgBoxName);
            }
        }

        // Take ListTaskForm (this) from the edit button selected
        // then send its values to AddTaskForm 
        private void EditButton_Response(object sender, ListTaskForm e)
        {
            m_todoPanel.Children.Remove(m_newButton);
            m_todoPanel.Children.Remove(e);

            m_addForm.ResetTexts();
            m_addForm.m_taskName.Text = e.m_task.Text;
            m_addForm.m_progressSize.Text = e.m_progress.Maximum.ToString();
            m_todoPanel.Children.Add(m_addForm);
        }

        // Combine All Progress Maximums
        // Increase progress size when started
        // Remove a progress when ended
        private void ChangeProgressMaximumState(bool isEnded, double max)
        {
            if (!isEnded) 
            {
                m_taskBar.ProgressState = TaskbarItemProgressState.Normal;
                m_progressMax += max;
            }
            else 
            {
                m_progress -= max;
                m_progressMax -= max;

                if (m_progressMax == 0)
                {
                    m_taskBar.ProgressState = TaskbarItemProgressState.None;
                }
            }
        }

        // Change ProgressValue using Progress Maximum
        private void ChangeProgressValueState(object sender, double e)
        {
            m_progress += e;
            m_taskBar.ProgressValue = (double)(m_progress / m_progressMax);
        }
    }
}
