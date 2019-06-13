using Demo1.Command;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace Demo1.ViewModel
{
    public class CategoryListViewModel : INotifyPropertyChanged
    {
        // Category List
        private ObservableCollection<CategoryViewModel> m_Category = new ObservableCollection<CategoryViewModel>();
        public ObservableCollection<CategoryViewModel> Category
        {
            get { return m_Category; }
            set
            {
                if (m_Category != value)
                {
                    m_Category = value;
                    NotifyPropertyChanged(nameof(m_Category));
                }
            }
        }

        // Temp Data
        public int Size { get; set; } = 0;
        public string CategoryName { get; set; } = "Enter Category name";
        public int ID { get; set; } = 0;
        public bool IsSelected { get; set; }
        //public bool CategorySelected { get; set; }

        // Command and Event Handler
        public ICommand CreateCategoryCommand { get { return new CreateCategoryCommand(); } }
        public ICommand DeleteCategoryCommand { get { return new DeleteCategoryCommand(); } }

        public event PropertyChangedEventHandler PropertyChanged;
        private void NotifyPropertyChanged(string name)
        {
            // if not null then invoke
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
        }
    }
}
