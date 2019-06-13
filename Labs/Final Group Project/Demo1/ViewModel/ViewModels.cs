using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.ViewModel
{
    public class ViewModels
    {
        public ViewModels()
        {
            m_TaskListViewModel = new TaskListViewModel();
            m_CategoryListViewModel = new CategoryListViewModel();      
        }

        public TaskListViewModel m_TaskListViewModel { get; set; }
        public CategoryListViewModel m_CategoryListViewModel { get; set; }
    }
}
