using System;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace DataResource
{
    /// <summary>
    /// Interaction logic for ListTaskForm.xaml
    /// </summary>
    public partial class ListTaskForm : UserControl
    {
        const int DELAY_1SEC = 1000;
        const int INCREMENT_SIZE = 1;

        public ListTaskForm()
        {
            InitializeComponent();
        }

        // Edit Button
        public event EventHandler<ListTaskForm> EditButton_StatusUpdate;
        private void Edit_ButtonClick(object sender, RoutedEventArgs e)
        {
            EditButton_StatusUpdate(null, this);
        }

        // Start Button to initiate a long-running process
        public event Action<bool, double> ProgressMaximum_StatusUpdate;
        private async void Start_ButtonClick(object sender, RoutedEventArgs e)
        {
            var button = sender as Button;
            int count = (int)m_progress.Maximum;
            var task = Task.Run(() => ExecuteLongProcedureAsync(count, DELAY_1SEC));

            button.IsEnabled = false;
            ProgressMaximum_StatusUpdate(button.IsEnabled, m_progress.Maximum);
            await task;
            button.IsEnabled = true;
            ProgressMaximum_StatusUpdate(button.IsEnabled, m_progress.Maximum);
        }

        // Start the long process using Lambdas
        public event EventHandler<double> ProgressValue_StatusUpdate;
        internal void ExecuteLongProcedureAsync(int count, int delay)
        {
            for (int i = 1; i < count + 1;)
            {
                System.Threading.Thread.Sleep(delay);
                Dispatcher.Invoke(() =>
                {
                    ProgressValue_StatusUpdate(null, INCREMENT_SIZE);
                    m_progress.Value = i;
                });

                i += INCREMENT_SIZE;
            }
        }
    }
}
