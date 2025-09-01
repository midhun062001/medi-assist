import NavBar from "../components/NavBar";

function Home() {
  return (
    <>
      <NavBar />
      <section className="w-screen bg-gray-50 pt-[65px]">
        <div className="h-[calc(100vh-65px)] w-full py-[75px] px-[100px]">
          <div className="h-full w-full flex">
            <div className="h-full w-[60%] flex flex-col justify-center  font-aileron">
              <div className="mb-[30px]">
                <h1 className="text-5xl font-black tracking-tighter text-blue-500 mb-[20px]">
                  <q> Your Health, Our Priority </q>
                </h1>
                <h5 className="w-[70%] font-aileron italic text-xl tracking-tight text-gray-700 font-bold">
                  Book appointments, manage medical records, and access trusted
                  doctors – all in one secure platform
                </h5>
              </div>
              <button
                type="button"
                className="w-fit px-18 py-3 bg-blue-800 rounded-lg text-gray-100 font-bold  font-aileron 
                hover:bg-blue-800/85 transition-all duration-200"
              >
                Book Now!
              </button>
            </div>
            <div className="h-full w-[40%]  flex items-center justify-center">
              <img
                src="src\assets\Mental health.gif"
                className="max-h-[92%] max-w-full"
              />
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default Home;
