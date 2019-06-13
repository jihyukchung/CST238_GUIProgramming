using Fluent;
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
    /// <summary>
    /// Interaction logic for RibbonUserControl.xaml
    /// </summary>
    public partial class RibbonUserControl : UserControl
    {
        public RibbonUserControl()
        {
            InitializeComponent();
        }

        // Change Themes
        public event EventHandler ThemeChange;
        private void ChangeStyle_ButtonClick(object sender, EventArgs e)
        {
            Fluent.Button button = (Fluent.Button)sender;
            string style_str = button.Content.ToString();

            // Acccording to the v6.1 source code opened using Blend, the current address is
            // Source="pack://application:,,,/Fluent;component/Themes/Accents/<ColorName>.Xaml"
            // This seems different in previous versions.
            Resources.MergedDictionaries.Clear();
            string fluentStyle_UrlStr = "pack://application:,,,/Fluent;component/Themes/Accents/" + style_str + ".xaml";
            //string otherStyle_UrlStr = "pack://application:,,,/Demo1;component/Style/" + style_str + ".xaml";
            ThemeChange(sender, e);

            if (style_str.Equals("Generic"))
            {
                fluentStyle_UrlStr = "pack://application:,,,/Fluent;component/Themes/" + style_str + ".xaml";
            }
            Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(fluentStyle_UrlStr, UriKind.RelativeOrAbsolute) });
            //Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = new Uri(otherStyle_UrlStr, UriKind.RelativeOrAbsolute) });
        }

        // Change Pages i.e.) home, budget, overview
        public event MouseButtonEventHandler PageChange;
        public void PageButton_PreviewMouseUp(object sender, MouseButtonEventArgs e)
        {
            if (PageChange != null)
            {
                PageChange(sender, e);
            }
        }
    }
}
