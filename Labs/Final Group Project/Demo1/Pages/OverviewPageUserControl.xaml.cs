using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Demo1.Pages
{
    public partial class OverviewPageUserControl : UserControl
    {
        public ExpenseCollection m_ec = new ExpenseCollection();

        public OverviewPageUserControl()
        {
            InitializeComponent();
            PieSeries.ItemsSource = m_ec;
        }
    }

    public class Expense : INotifyPropertyChanged
    {
        public string Name
        {
            get;
            set;
        }
        public int Amount
        {
            get;
            set;
        }

        public bool Expected
        {
            get;
            set;
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public void NotifyPropertyChanged(string propName)
        {
            if (this.PropertyChanged != null)
                this.PropertyChanged(this, new PropertyChangedEventArgs(propName));
        }
    }

    public class ExpenseCollection : ObservableCollection<Expense>
    {
        public void Add_Expense(string name, string amount, bool expected)
        {
            int cost = Convert.ToInt32(amount);
            Add(new Expense { Name = name, Amount = cost, Expected = expected });
        }
    }
}
