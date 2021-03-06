package com.activenetwork.qa.awo.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ProgressMonitor;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.UserInfo;

public class OrmsFTPTest {

	public static void main(String[] arg) {

		try {

			//////

			JSch jsch = new JSch();
			Session session = jsch.getSession("jboss2", "10.10.80.152", 22);
			session.setPassword("fry");

			java.util.Properties config1 = new java.util.Properties();
			config1.put("StrictHostKeyChecking", "no");
			session.setConfig(config1);

			////

			/*		JSch jsch=new JSch();
			 
			 String host=JOptionPane.showInputDialog("Enter username@hostname",
			 System.getProperty("user.name")+
			 "@localhost"); 
			 String user=host.substring(0, host.indexOf('@'));
			 host=host.substring(host.indexOf('@')+1);
			 
			 Session session=jsch.getSession(user, host, 22);
			 
			 // username and password will be given via UserInfo interface.
			 UserInfo ui=new MyUserInfo();
			 session.setUserInfo(ui);
			 */
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp c = (ChannelSftp) channel;

			java.io.InputStream in = System.in;
			java.io.PrintStream out = System.out;

			List<String> cmds = new ArrayList<String>();
			byte[] buf = new byte[1024];
			int i;
			String str;
			int level = 0;

			while (true) {
				out.print("sftp> ");
				cmds.clear();
				i = in.read(buf, 0, 1024);
				if (i <= 0)
					break;

				i--;
				if (i > 0 && buf[i - 1] == 0x0d)
					i--;
				//str=new String(buf, 0, i);
				//System.out.println("|"+str+"|");
				int s = 0;
				for (int ii = 0; ii < i; ii++) {
					if (buf[ii] == ' ') {
						if (ii - s > 0) {
							cmds.add(new String(buf, s, ii - s));
						}
						while (ii < i) {
							if (buf[ii] != ' ')
								break;
							ii++;
						}
						s = ii;
					}
				}
				if (s < i) {
					cmds.add(new String(buf, s, i - s));
				}
				if (cmds.size() == 0)
					continue;

				String cmd = cmds.get(0);

				if (cmd.equals("quit")) {
					c.quit();
					break;
				}
				if (cmd.equals("exit")) {
					c.exit();
					break;
				}
				if (cmd.equals("rekey")) {
					session.rekey();
					continue;
				}
				if (cmd.equals("compression")) {
					if (cmds.size() < 2) {
						out.println("compression level: " + level);
						continue;
					}
					try {
						level = Integer.parseInt(cmds.get(1));
						Hashtable<String,String> config = new Hashtable<String,String>();
						if (level == 0) {
							config.put("compression.s2c", "none");
							config.put("compression.c2s", "none");
						} else {
							config.put("compression.s2c", "zlib,none");
							config.put("compression.c2s", "zlib,none");
						}
						session.setConfig(config);
					} catch (Exception e) {
					}
					continue;
				}
				if (cmd.equals("cd") || cmd.equals("lcd")) {
					if (cmds.size() < 2)
						continue;
					String path = cmds.get(1);
					try {
						if (cmd.equals("cd"))
							c.cd(path);
						else
							c.lcd(path);
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}
				if (cmd.equals("rm") || cmd.equals("rmdir")
						|| cmd.equals("mkdir")) {
					if (cmds.size() < 2)
						continue;
					String path = cmds.get(1);
					try {
						if (cmd.equals("rm"))
							c.rm(path);
						else if (cmd.equals("rmdir"))
							c.rmdir(path);
						else
							c.mkdir(path);
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}
				if (cmd.equals("chgrp") || cmd.equals("chown")
						|| cmd.equals("chmod")) {
					if (cmds.size() != 3)
						continue;
					String path = cmds.get(2);
					int foo = 0;
					if (cmd.equals("chmod")) {
						byte[] bar = cmds.get(1).getBytes();
						int k;
						for (int j = 0; j < bar.length; j++) {
							k = bar[j];
							if (k < '0' || k > '7') {
								foo = -1;
								break;
							}
							foo <<= 3;
							foo |= (k - '0');
						}
						if (foo == -1)
							continue;
					} else {
						try {
							foo = Integer.parseInt(cmds.get(1));
						} catch (Exception e) {
							continue;
						}
					}
					try {
						if (cmd.equals("chgrp")) {
							c.chgrp(foo, path);
						} else if (cmd.equals("chown")) {
							c.chown(foo, path);
						} else if (cmd.equals("chmod")) {
							c.chmod(foo, path);
						}
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}
				if (cmd.equals("pwd") || cmd.equals("lpwd")) {
					str = (cmd.equals("pwd") ? "Remote" : "Local");
					str += " working directory: ";
					if (cmd.equals("pwd"))
						str += c.pwd();
					else
						str += c.lpwd();
					out.println(str);
					continue;
				}
				if (cmd.equals("ls") || cmd.equals("dir")) {
					String path = ".";
					if (cmds.size() == 2)
						path = cmds.get(1);
					try {
						List<?> vv = c.ls(path);
						if (vv != null) {
							for (int ii = 0; ii < vv.size(); ii++) {
								//		out.println(vv.elementAt(ii).toString());

								Object obj = vv.get(ii);
								if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
									out
											.println(((com.jcraft.jsch.ChannelSftp.LsEntry) obj)
													.getLongname());
								}

							}
						}
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}
				if (cmd.equals("lls") || cmd.equals("ldir")) {
					String path = ".";
					if (cmds.size() == 2)
						path = cmds.get(1);
					try {
						java.io.File file = new java.io.File(path);
						if (!file.exists()) {
							out.println(path + ": No such file or directory");
							continue;
						}
						if (file.isDirectory()) {
							String[] list = file.list();
							for (int ii = 0; ii < list.length; ii++) {
								out.println(list[ii]);
							}
							continue;
						}
						out.println(path);
					} catch (Exception e) {
						System.out.println(e);
					}
					continue;
				}
				if (cmd.equals("get") || cmd.equals("get-resume")
						|| cmd.equals("get-append") || cmd.equals("put")
						|| cmd.equals("put-resume") || cmd.equals("put-append")) {
					if (cmds.size() != 2 && cmds.size() != 3)
						continue;
					String p1 = cmds.get(1);
					//	  String p2=p1;
					String p2 = ".";
					if (cmds.size() == 3)
						p2 = cmds.get(2);
					try {
						SftpProgressMonitor monitor = new MyProgressMonitor();
						if (cmd.startsWith("get")) {
							int mode = ChannelSftp.OVERWRITE;
							if (cmd.equals("get-resume")) {
								mode = ChannelSftp.RESUME;
							} else if (cmd.equals("get-append")) {
								mode = ChannelSftp.APPEND;
							}
							c.get(p1, p2, monitor, mode);
						} else {
							int mode = ChannelSftp.OVERWRITE;
							if (cmd.equals("put-resume")) {
								mode = ChannelSftp.RESUME;
							} else if (cmd.equals("put-append")) {
								mode = ChannelSftp.APPEND;
							}
							c.put(p1, p2, monitor, mode);
						}
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}

				if (cmd.equals("ln") || cmd.equals("symlink")
						|| cmd.equals("rename")) {
					if (cmds.size() != 3)
						continue;
					String p1 = cmds.get(1);
					String p2 = cmds.get(2);
					try {
						if (cmd.equals("rename"))
							c.rename(p1, p2);
						else
							c.symlink(p1, p2);
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					continue;
				}
				if (cmd.equals("stat") || cmd.equals("lstat")) {
					if (cmds.size() != 2)
						continue;
					String p1 = cmds.get(1);
					SftpATTRS attrs = null;
					try {
						if (cmd.equals("stat"))
							attrs = c.stat(p1);
						else
							attrs = c.lstat(p1);
					} catch (SftpException e) {
						System.out.println(e.getMessage());
					}
					if (attrs != null) {
						out.println(attrs);
					} else {
					}
					continue;
				}
				if (cmd.equals("version")) {
					out.println("SFTP protocol version " + c.version());
					continue;
				}
				if (cmd.equals("help") || cmd.equals("?")) {
					out.println(help);
					continue;
				}
				out.println("unimplemented command: " + cmd);
			}
			session.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.exit(0);
	}

	public static class MyUserInfo implements UserInfo {
		public String getPassword() {
			return passwd;
		}

		public boolean promptYesNo(String str) {
			Object[] options = { "yes", "no" };
			int foo = JOptionPane.showOptionDialog(null, str, "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);
			return foo == 0;
		}

		String passwd;

		JTextField passwordField = (JTextField) new JPasswordField(20);

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			Object[] ob = { passwordField };
			int result = JOptionPane.showConfirmDialog(null, ob, message,
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				passwd = passwordField.getText();
				return true;
			} else {
				return false;
			}
		}

		public void showMessage(String message) {
			JOptionPane.showMessageDialog(null, message);
		}
	}

	/*
	 public static class MyProgressMonitor implements com.jcraft.jsch.ProgressMonitor{
	 JProgressBar progressBar;
	 JFrame frame;
	 long count=0;
	 long max=0;
	 
	 public void init(String info, long max){
	 this.max=max;
	 if(frame==null){
	 frame=new JFrame();
	 frame.setSize(200, 20);
	 progressBar = new JProgressBar();
	 }
	 count=0;
	 
	 frame.setTitle(info);
	 progressBar.setMaximum((int)max);
	 progressBar.setMinimum((int)0);
	 progressBar.setValue((int)count);
	 progressBar.setStringPainted(true);
	 
	 JPanel p=new JPanel();
	 p.add(progressBar);
	 frame.getContentPane().add(progressBar);
	 frame.setVisible(true);
	 System.out.println("!info:"+info+", max="+max+" "+progressBar);
	 }
	 public void count(long count){
	 this.count+=count;
	 System.out.println("count: "+count);
	 progressBar.setValue((int)this.count);
	 }
	 public void end(){
	 System.out.println("end");
	 progressBar.setValue((int)this.max);
	 frame.setVisible(false);
	 }
	 }
	 */

	public static class MyProgressMonitor implements SftpProgressMonitor {
		ProgressMonitor monitor;

		long count = 0;

		long max = 0;

		public void init(int op, String src, String dest, long max) {
			this.max = max;
			monitor = new ProgressMonitor(null,
					((op == SftpProgressMonitor.PUT) ? "put" : "get") + ": "
							+ src, "", 0, (int) max);
			count = 0;
			percent = -1;
			monitor.setProgress((int) this.count);
			monitor.setMillisToDecideToPopup(1000);
		}

		private long percent = -1;

		public boolean count(long count) {
			this.count += count;

			if (percent >= this.count * 100 / max) {
				return true;
			}
			percent = this.count * 100 / max;

			monitor.setNote("Completed " + this.count + "(" + percent
					+ "%) out of " + max + ".");
			monitor.setProgress((int) this.count);

			return !(monitor.isCanceled());
		}

		public void end() {
			monitor.close();
		}
	}

	private static String help = "      Available commands:\n"
			+ "      * means unimplemented command.\n"
			+ "cd path                       Change remote directory to 'path'\n"
			+ "lcd path                      Change local directory to 'path'\n"
			+ "chgrp grp path                Change group of file 'path' to 'grp'\n"
			+ "chmod mode path               Change permissions of file 'path' to 'mode'\n"
			+ "chown own path                Change owner of file 'path' to 'own'\n"
			+ "help                          Display this help text\n"
			+ "get remote-path [local-path]  Download file\n"
			+ "get-resume remote-path [local-path]  Resume to download file.\n"
			+ "get-append remote-path [local-path]  Append remote file to local file\n"
			+ "*lls [ls-options [path]]      Display local directory listing\n"
			+ "ln oldpath newpath            Symlink remote file\n"
			+ "*lmkdir path                  Create local directory\n"
			+ "lpwd                          Print local working directory\n"
			+ "ls [path]                     Display remote directory listing\n"
			+ "*lumask umask                 Set local umask to 'umask'\n"
			+ "mkdir path                    Create remote directory\n"
			+ "put local-path [remote-path]  Upload file\n"
			+ "put-resume local-path [remote-path]  Resume to upload file\n"
			+ "put-append local-path [remote-path]  Append local file to remote file.\n"
			+ "pwd                           Display remote working directory\n"
			+ "stat path                     Display info about path\n"
			+ "exit                          Quit sftp\n"
			+ "quit                          Quit sftp\n"
			+ "rename oldpath newpath        Rename remote file\n"
			+ "rmdir path                    Remove remote directory\n"
			+ "rm path                       Delete remote file\n"
			+ "symlink oldpath newpath       Symlink remote file\n"
			+ "rekey                         Key re-exchanging\n"
			+ "compression level             Packet compression will be enabled\n"
			+ "version                       Show SFTP version\n"
			+ "?                             Synonym for help";
}
