import Footer from "../components/footer.jsx";
import Header from "../components/header.jsx";
import Sidebar from "../components/sidebar.jsx";

function MainLayout({ children }) {
	return (
		<div>
			<Header />
			<Sidebar />
			<div className="ml-[15%] mr-[1%]">{children}</div>
			<Footer />
		</div>
		{/* 
	<div>
			<Header />
			<Sidebar />
			<div className="ml-[15%] mr-[1%]">
				<center>
					<h1
						id="title"
						className=" mt-4 text-5xl tracking-wide text-neutral-600 "
					>
						Add New Driver
					</h1>

					<br />
					<hr />
					<Link to="/driver">
						<div className="w-[10%] float-left text-neutral-600 border-2 border-neutral-500  border-solid rounded-full h-fit w-fit text-center px-2 py-1 m-2 cursor-pointer hover:text-white hover:bg-neutral-500">
							<i className="lni lni-arrow-left"> </i> Back
						</div>
					</Link>
				</center>
			</div>
			<Footer />
		</div>
	*/}
	);
}
