using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TicTacToe
{
    public class PlayerMove : INotifyPropertyChanged
    {
        public PlayerMove() : this("X", 0) { }
        public PlayerMove(string name, int move_number)
        {
            this.playerName = name;
            this.moveNumber = move_number;
        }

        // INotifyPropertyChanged Member
        public event PropertyChangedEventHandler PropertyChanged;
        void Notify(string propName)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(propName));
            }
        }

        private string playerName;
        public string PlayerName
        {
            get { return playerName; }
            set
            {
                playerName = value;
                Notify("PlayerName");
            }
        }

        private int moveNumber;
        public int MoveNumber
        {
            get { return moveNumber; }
            set { moveNumber = value; }
        }
    }
}