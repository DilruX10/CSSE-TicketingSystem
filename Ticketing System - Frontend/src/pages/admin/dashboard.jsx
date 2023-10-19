import { Link } from "react-router-dom";
import AnalysisIcon from "../../assets/analysis.svg";
import BusIcon from "../../assets/bus.svg";
import DriverIcon from "../../assets/driver.svg";
import RouteIcon from "../../assets/route.svg";
import ScheduleIcon from "../../assets/schedule.svg";
import Header from "../../components/header.jsx";
import Sidebar from "../../components/sidebar.jsx";

export default function Dashboard() {
	return (
		<div>
			<Header />
			<Sidebar />
			<div className="ml-[15%] mr-[1%]">
				<div>
					<center>
						<h1
							id="title"
							className=" mt-4 text-5xl tracking-wide text-neutral-600 "
						>
							Dashboard
						</h1>

						<br />
						<hr />
						<br />
						<div className="w-full h-full flex flex-col items-center">
							<div className="w-full flex flex-row">
								<Link to="/bus" className="basis-1/3">
									<div className="m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={BusIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Buses
										</span>
									</div>
								</Link>

								<Link to="/driver" className="basis-1/3">
									<div className="basis-1/3 m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={DriverIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Drivers
										</span>
									</div>
								</Link>

								<Link to="/route" className="basis-1/3">
									<div className="m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={RouteIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Routes
										</span>
									</div>
								</Link>
							</div>

							<div className="w-full flex flex-row ">
								<Link to="/schedule" className="basis-1/3">
									<div className="m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={ScheduleIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Schedule
										</span>
									</div>
								</Link>

								<Link to="/finance" className="basis-1/3">
									<div className="m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={AnalysisIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Finance
										</span>
									</div>
								</Link>

								<Link to="/traffic" className="basis-1/3">
									<div className="m-2 p-2 border border-solid rounded-xl h-48 flex flex-col items-center hover:border-4">
										<img
											className=" m-3 w-fit h-48"
											src={AnalysisIcon}
										></img>
										<span className="m-2 p-2 w-full text-2xl ">
											Traffic
										</span>
									</div>
								</Link>
							</div>
						</div>
					</center>
				</div>
			</div>
		</div>
	);
}
