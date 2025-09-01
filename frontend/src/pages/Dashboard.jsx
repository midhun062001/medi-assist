import {
  BadgeDollarSign,
  Clock,
  HeartPlus,
  LayoutDashboard,
  Notebook,
  Settings,
} from "lucide-react";
import NavBar from "../components/NavBar";

function Dashboard() {
  return (
    <>
      <NavBar />
      <section className="bg-gray-200 max-w-screen h-[100dvh] pt-[65px] flex">
        {/* Sidebar */}
        <aside className="h-[100dvh-65px] w-[18%] bg-blue-900 text-white py-[42px] px-[24px] flex flex-col">
          {/* Profile Section */}
          <div className="flex flex-col items-center mb-8 gap-2">
            <img
              src="https://avatar.iran.liara.run/public/23"
              alt="Profile"
              className="w-20 h-20 rounded-full border-2 border-white mb-3"
            />
            <h3 className="text-lg font-semibold">John Doe</h3>
            <p className="text-sm text-blue-200">Patient ID: P12345</p>
          </div>

          {/* Menu */}
          <nav>
            <ul className="space-y-7">
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <LayoutDashboard size={18} />
                Dashboard
              </li>
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <Clock size={18} />
                Appointments
              </li>
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <HeartPlus size={18} />
                Doctors
              </li>
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <Notebook size={18} />
                Medical Records
              </li>
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <BadgeDollarSign size={18} />
                Billing
              </li>
              <li className="cursor-pointer hover:text-gray-300 flex items-center gap-[10px]">
                <Settings size={18} />
                Settings
              </li>
            </ul>
          </nav>
        </aside>

        {/* Main Content */}
        <main className="w-[82%] py-6 px-8 max-h-screen overflow-auto">
          {/* Overview / Top Section */}
          <div className="grid grid-cols-6 gap-6 mb-6">
            <div className="col-span-6 h-[200px] rounded-lg bg-white shadow p-6">
              <h2 className="text-xl font-semibold mb-3">Welcome, John 👋</h2>
              <p className="text-gray-600">
                Here's a quick overview of your health activities today.
              </p>
              <ul className="list-disc list-inside mt-4 text-gray-700 space-y-1">
                <li>2 upcoming appointments this week</li>
                <li>3 new medical reports uploaded</li>
                <li>1 unpaid bill pending</li>
              </ul>
            </div>
          </div>

          {/* Stats / Cards Section */}
          <div className="grid grid-cols-6 gap-6 mb-6">
            <div className="col-span-2 h-[150px] rounded-lg bg-white shadow p-6 flex flex-col justify-center">
              <h3 className="text-lg font-semibold">Appointments</h3>
              <p className="text-gray-600 mt-2">
                Next: Sep 2, 2025 with Dr. Smith
              </p>
              <span className="text-sm text-green-600 mt-1">2 upcoming</span>
            </div>
            <div className="col-span-2 h-[150px] rounded-lg bg-white shadow p-6 flex flex-col justify-center">
              <h3 className="text-lg font-semibold">Bills</h3>
              <p className="text-gray-600 mt-2">Pending: $120</p>
              <span className="text-sm text-red-600 mt-1">1 unpaid</span>
            </div>
            <div className="col-span-2 h-[150px] rounded-lg bg-white shadow p-6 flex flex-col justify-center">
              <h3 className="text-lg font-semibold">Reports</h3>
              <p className="text-gray-600 mt-2">
                Blood Test uploaded on Aug 25
              </p>
              <span className="text-sm text-blue-600 mt-1">3 new reports</span>
            </div>
          </div>

          {/* Lower Grid Section */}
          <div className="grid grid-cols-6 gap-6">
            {/* Medical Records */}
            <div className="col-span-4 h-[300px] rounded-lg bg-white shadow p-6 overflow-auto">
              <h3 className="text-lg font-semibold mb-3">Medical Records</h3>
              <ul className="space-y-2 text-gray-700">
                <li className="flex justify-between">
                  <span>Blood Test</span>
                  <span className="text-sm text-gray-500">Aug 25, 2025</span>
                </li>
                <li className="flex justify-between">
                  <span>X-Ray - Chest</span>
                  <span className="text-sm text-gray-500">Aug 10, 2025</span>
                </li>
                <li className="flex justify-between">
                  <span>ECG Report</span>
                  <span className="text-sm text-gray-500">Jul 28, 2025</span>
                </li>
              </ul>
            </div>

            {/* Notifications */}
            <div className="col-span-2 h-[300px] rounded-lg bg-white shadow p-6 overflow-auto">
              <h3 className="text-lg font-semibold mb-3">Notifications</h3>
              <ul className="space-y-3 text-gray-700">
                <li>Appointment confirmed with Dr. Smith</li>
                <li>Reminder: Bill of $120 pending</li>
                <li>New report: Blood Test uploaded</li>
              </ul>
            </div>
          </div>
        </main>
      </section>
    </>
  );
}

export default Dashboard;
