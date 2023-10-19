import { Link } from "react-router-dom";

export default function sidebar() {
	return (
		<div className="inset-y-20 w-[14%] h-full fixed bg-indigo-500 h-20 flex flex-col">
			<div className="rounded-full m-2 px-2 py-1 text-2xl"> </div>
			<div className="rounded-full m-2 px-2 py-1 text-2xl"> </div>

			<Link to="/dashboard">
				<div className="text-blue-50 border-2 border-transparent my-2	 mx-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-postcard"></i>
					&nbsp;&nbsp;
					<span>Dashboard</span>
				</div>
			</Link>

			<Link to="/bus">
				<div className="text-blue-50 border-2 border-transparent my-2	 mx-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-bus"></i> &nbsp;&nbsp;<span>Buses</span>
				</div>
			</Link>

			<Link to="/driver">
				<div className="text-blue-50 border-2 border-transparent my-2	 mx-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-users"></i>&nbsp;&nbsp;<span>Drivers</span>
				</div>
			</Link>

			<Link to="/route">
				<div className="text-blue-50 border-2 border-transparent my-2	 mx-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-map"></i>&nbsp;&nbsp;
					<span>Routes</span>
				</div>
			</Link>

			<Link to="/schedule">
				<div className="text-blue-50 border-2 border-transparent my-2	 mx-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-calendar"></i>&nbsp;&nbsp;
					<span>Schedule</span>
				</div>
			</Link>

			<Link to="/finance">
				<div className="text-blue-50 m-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-graph"></i>&nbsp;&nbsp;
					<span>Finance</span>
				</div>
			</Link>

			<Link to="/traffic">
				<div className="text-blue-50 m-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
					<i className="lni lni-map-marker"></i>&nbsp;&nbsp;
					<span>Traffic</span>
				</div>
			</Link>
			<div className="text-blue-50 m-2 px-4 py-2 text-2xl flex items-center hover:bg-indigo-700 hover:rounded-lg cursor-pointer">
				<i className="lni lni-coin"></i>&nbsp;&nbsp;
				<span>Fare</span>
			</div>
		</div>
	);
}
