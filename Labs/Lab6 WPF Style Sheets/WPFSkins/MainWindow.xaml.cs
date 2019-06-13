/************************************************************************
 * File	Name	WPFSkins
 * Author		Jihyuk Chung
 * Date			May 07, 2018 Tue - Part 1 ~ 5
 * Modified		May 10, 2018 Wed - Added Part 6
 * 				May 14, 2018 Sun - Added Part 7
 * 				                 - Dynamic Dictionary Bug Fix
 * 
 * Class		CST 238 - GUI Programming
 * Professor	Philip Howard Ph.D.
 * 
 ***********************************************************************/

using System;
using System.IO;
using System.Windows;
using System.Windows.Markup;

namespace WPFSkins
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static int m_style = 0;

        public MainWindow()
        {
            InitializeComponent();     
        }

        // Button Default
        private void Default_ButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_style != 0)
            {
                Resources.MergedDictionaries.Clear();
                Uri url = new Uri("pack://application:,,,/WPFSkins;component/DefaultStyle.xaml", UriKind.RelativeOrAbsolute);
                Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = url });
                m_style = 0;
            }
        }

        // Button 1
        private void Style1_ButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_style != 1)
            {
                Resources.MergedDictionaries.Clear();
                Uri url = new Uri("pack://application:,,,/WPFSkins;component/Style1.xaml", UriKind.RelativeOrAbsolute);
                Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = url });
                m_style = 1;
            }
        }

        // Button 2
        private void Style2_ButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_style != 2)
            {
                Resources.MergedDictionaries.Clear();
                Uri url = new Uri("pack://application:,,,/WPFSkins;component/Style2.xaml", UriKind.RelativeOrAbsolute);
                Resources.MergedDictionaries.Add(new ResourceDictionary() { Source = url });
                m_style = 2;
            }
        }

        // Button 3
        private void Style3_ButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_style != 3)
            {
                ResourceDictionary resources = null;
                String loc = Directory.GetParent(Environment.CurrentDirectory).Parent.FullName + "\\Style3.xaml";
                using (FileStream fs = new FileStream(loc, FileMode.Open, FileAccess.Read))
                {
                    // Get the root element:must be a ResourceDictionary
                    resources = (ResourceDictionary)XamlReader.Load(fs);
                }

                Resources.MergedDictionaries.Clear();
                Resources.MergedDictionaries.Add(resources);
            }
        }

        // Button 4
        private void Style4_ButtonClick(object sender, RoutedEventArgs e)
        {
            if (m_style != 4)
            {
                ResourceDictionary resources = null;
                String loc = Directory.GetParent(Environment.CurrentDirectory).Parent.FullName + "\\Style4.xaml";
                using (FileStream fs = new FileStream(loc, FileMode.Open, FileAccess.Read))
                {
                    // Get the root element:must be a ResourceDictionary
                    resources = (ResourceDictionary)XamlReader.Load(fs);
                }

                Resources.MergedDictionaries.Clear();
                Resources.MergedDictionaries.Add(resources);
            }
        }
    }
}
