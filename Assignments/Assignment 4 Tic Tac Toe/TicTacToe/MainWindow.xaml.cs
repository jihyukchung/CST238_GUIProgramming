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

namespace TicTacToe
{
    public partial class MainWindow : Window
    {
        private PlayerMove playerMove = new PlayerMove();

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            if (button.Content == null)
            {
                button.Content = new PlayerMove(playerMove.PlayerName, ++playerMove.MoveNumber);
                ChangePlayerName();
                statusTextBlock.Text = "It's your turn, " + playerMove.PlayerName;             
            }
        }

        private void ChangePlayerName()
        {
            if (playerMove.PlayerName == "X")
            {
                playerMove.PlayerName = "O";
            }
            else
            {
                playerMove.PlayerName = "X";
            }
        }
    }
}
