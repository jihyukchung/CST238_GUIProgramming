using System;
using System.Collections.Generic;
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
using Demo1.ViewModel;
using System.Text.RegularExpressions;
using Fluent;
using Demo1.Pages;

namespace Demo1
{
    public partial class MainWindow
    {
        private ViewModels viewmodels = new ViewModels();

        public MainWindow()
        {
            InitializeComponent();
            LoadEventHandlers();

            // Enter Experimental Data
            // Remove this for release
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -10.00, m_categoryName = "None" });
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -100.00, m_categoryName = "School Supplies" });
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -150.00, m_categoryName = "Shopping" });
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -150.00, m_categoryName = "Shopping" });
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -100.00, m_categoryName = "School Supplies" });
            viewmodels.m_TaskListViewModel.Tasks.Add(new TaskViewModel() { m_datetime = DateTime.Now.Date, m_amount = -10.00, m_categoryName = "None" });
            viewmodels.m_CategoryListViewModel.Category.Add(new CategoryViewModel() { m_category = "None", m_id = 0 , m_selected = true });
            viewmodels.m_CategoryListViewModel.Category.Add(new CategoryViewModel() { m_category = "Shopping", m_id = 2 });
            viewmodels.m_CategoryListViewModel.Category.Add(new CategoryViewModel() { m_category = "School Supplies", m_id = 3 });

            // Set Data Context to ViewModels, a View Model that Combine All Other View Models
            this.DataContext = viewmodels;
        }

        // Load Event Handlers from UserControls
        public void LoadEventHandlers()
        {
            x_Ribbon.PageChange += new MouseButtonEventHandler(PageButton_Click);
            x_Ribbon.ThemeChange += new EventHandler(ChangeTheme_Click);
        }

        // Change Themes
        private void ChangeTheme_Click(object sender, EventArgs e)
        {
            Fluent.Button button = (Fluent.Button)sender;
            string style_str = button.Content.ToString();

            Resources.MergedDictionaries.Clear();
            string otherStyle_UrlStr = "pack://application:,,,/Demo1;component/Style/" + style_str + ".xaml";
            Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(otherStyle_UrlStr, UriKind.RelativeOrAbsolute) });

            x_HomePage.Resources.MergedDictionaries.Clear();
            x_HomePage.Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(otherStyle_UrlStr, UriKind.RelativeOrAbsolute) });
        }

        // Change Pages by Resetting Visibilities
        // This Event Handler Comes From RibbonUserControl.xaml
        private void PageButton_Click(object sender, RoutedEventArgs e)
        {
            RibbonTabItem button = (RibbonTabItem)sender;

            if (button.Header.Equals("Home"))
            {
                x_HomePage.Visibility = Visibility.Visible;
                x_OverviewPage.Visibility = Visibility.Hidden;
            }
            else if (button.Header.Equals("Overview"))
            {
                x_HomePage.Visibility = Visibility.Hidden;
                x_OverviewPage.Visibility = Visibility.Visible;
                CalculateData();
            }
        }

        // Calculate Data and Send Them to ExpenseCollection in the Overview Page
        private void CalculateData()
        {
            x_OverviewPage.m_ec.Clear();

            foreach (var categories in viewmodels.m_CategoryListViewModel.Category)
            {
                double amount = 0;
                foreach (var tasks in viewmodels.m_TaskListViewModel.Tasks)
                {
                    if (tasks.m_categoryName == categories.m_category)
                    {
                        amount += tasks.m_amount;
                    }
                }

                x_OverviewPage.m_ec.Add_Expense(categories.m_category, amount.ToString(), true);
            }
        }
    }
}
