using Demo1.ViewModel;
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

namespace Demo1.Pages
{
    public partial class HomePageUserControl : UserControl
    {
        public HomePageUserControl()
        {
            InitializeComponent();
        }

        // Plus minus Button values 
        private bool isPlus = true;
        private void PlusMinusButton_Click(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            isPlus = !isPlus;

            if (isPlus)
            {
                button.Content = "+";
            }
            else
            {
                button.Content = "-";
            }
        }

        // Make it so TextBox for $amount only accepts numbers and comma
        private void TextBox_PreviewKeyDown(object sender, KeyEventArgs e)
        {
            if (!(  ((e.Key >= Key.D0) && (e.Key <= Key.D9)) ||  
                    ((e.Key >= Key.NumPad0) && (e.Key <= Key.NumPad9)) || 
                    (e.Key == Key.Back) || (e.Key == Key.Decimal) || (e.Key == Key.OemPeriod)
               ))
            {
                //e.Key == Key.OemComma
                MessageBox.Show("Please enter numbers only.");
                e.Handled = true;
            }

            if ((e.Key == Key.OemPeriod || e.Key == Key.Decimal)
                && InputTextBox.Text.Contains("."))
            {
                MessageBox.Show("Decimal Sign Already Present.");
                e.Handled = true;
            }
        }

        // Make it so TextBox for $amount will select the entire string
        // when clicked
        private void TextBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
            TextBox textbox = (TextBox)sender;
            textbox.SelectAll();
        }

        // Open Choose CategoryWindow
        private void CategoryButton_Click(object sender, RoutedEventArgs e)
        {
            var category = new OpenCategoryWindow();

            category.Resources.MergedDictionaries.Clear();
            string otherStyle_UrlStr = "";
            if (this.Resources.MergedDictionaries.Count != 0)
            {
                otherStyle_UrlStr = this.Resources.MergedDictionaries[0].Source.ToString();
                category.Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(otherStyle_UrlStr, UriKind.RelativeOrAbsolute) });
            }
           
            category.DataContext = this.DataContext;
            category.Show();
            //category.Topmost = true;
        }
    }
}
