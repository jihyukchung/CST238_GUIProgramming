using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

using Demo1.Command;

namespace Demo1.ViewModel
{
    public class TaskListViewModel : INotifyPropertyChanged
    {
        // Task List
        private ObservableCollection<TaskViewModel> m_tasks = new ObservableCollection<TaskViewModel>();
        public ObservableCollection<TaskViewModel> Tasks
        {
            get { return m_tasks; }
            set
            {
                if (m_tasks != value)
                {
                    m_tasks = value;
                    NotifyPropertyChanged(nameof(m_tasks));
                }
            }
        }

        // Temp Data
        public DateTime TaskDateTime { get; set; } = DateTime.Now;
        public string TaskAmount { get; set; } = "0.0";
        public double TaskCategoryID { get; set; }
        public string TaskSign { get; set; } = "+";

        // Command and Event Handler
        public ICommand CreateTaskCommand { get { return new CreateTaskCommand(); } }

        public event PropertyChangedEventHandler PropertyChanged;
        private void NotifyPropertyChanged(string name)
        {
            // if not null then invoke
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
        }
    }
}
