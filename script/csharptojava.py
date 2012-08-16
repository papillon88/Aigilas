import os,shutil,re,sys

start_path = "c:\\_z\\dev\\git\\aigilas"
convert_path = ".\\convert\\"
targetName = "" #Minions
targetFile = ""#"Minions.java"
targetUrl = ""#'creatures'

def divide_class(targetName,targetFile,targetUrl):
	targetPath = os.path.join("c:\\_z\\dev\\git\\aigilas\\script\\convert\\com\\aigilas",targetUrl)
	targetPath = os.path.join(targetPath,targetFile)
	phase = 0
	braceCount = 0
	if os.path.exists('header.java'):
		os.remove('header.java')
	shutil.copyfile(targetPath,targetPath+'b')
	while phase < 3:
		newClassName = ''
		for line in open(targetPath+'b','r').read().splitlines():
			if phase == 0:
				if 'package' in line or 'import' in line:
					w = open('header.java','a')
					w.write(line+"\r")
					w.close()
			if phase == 1:
				if newClassName != '':
					w = open(targetPath.replace(targetName,newClassName),'a')
				if ' class ' in line:
					print "Generating " + newClassName+".java"
					newClassName = line.split('class')[1].rstrip().split('extends')[0].replace(' ','')						
					newPath = targetPath.replace(targetName,newClassName)
					if os.path.exists(newPath):
						os.remove(newPath)
					w = open(newPath,'a')
					braceCount = 0					
					header = open('header.java','r')
					for head in header:
						w.write(head)
					if not 'public' in line:
						line = 'public ' + line					
				if '{' in line:
					braceCount += 1
				if '}' in line:
					braceCount -= 1
				if newClassName != '':
					w.write(line+"\r")
					w.close()
		phase += 1
	os.remove(targetPath+'b')

def isCodeOnly(file):
	exclude = ['.txt','AssemblyInfo','.csproj','.csv']
	for ex in exclude:
		if ex in file:
			return False
	if ".cs" in file or ".java" in file:
		return True
	return False

traversed = []
def isCodeDir(dir):
	excludes = ['Debug','Release','TempPE','bin','obj','Properties','x86','script','concept','.git','port-workspace']
	excludes.append('SPX.Util')
	excludes.append('SPX.States')
	excludes.append('SPX.Core')
	excludes.append('SPX.Text')
	excludes.append('SPX.Particles')
	excludes.append('SPX.Entities')
	excludes.append('SPX.DevTools')
	excludes.append('SPX.Sprites')
	excludes.append('SPX.IO')
	excludes.append('SPX.Paths')
	for ex in excludes:
		if ex in dir:
			return False
	if dir in traversed:
		return False
	traversed.append(dir)
	return True

def cs2java(line):
	replacements = {'const':'static final','readonly ':'','string':'String','Dictionary':'HashMap', 'bool':'boolean','Int32':'Integer','this(':'initThis(','base(':'super(', '<int':'<Integer','int>':'Integer>','IList':'List','<float':'<Float','float>':'Float>','<boolean':'<Boolean','boolean>':'Boolean>','new List':'new ArrayList','base.':'super.','.Count()':'.length'}
	replacements['Aigilas.'] = 'com.aigilas.'
	replacements['SPX.'] = 'com.spx.'
	replacements['static class'] = 'class'
	replacements['ref '] = ''
	replacements['ContainsKey '] = 'containsKey'
	replacements['ToString '] = 'toString'
	replacements['virtual '] = ''
	replacements[' in '] = ':'
	replacements['foreach'] = 'for'
	replacements['.Contains'] = '.contains'
	replacements['.Add'] = '.add'
	replacements['.Remove'] = '.remove'
	replacements['RNG.Rand.Next'] = 'RNG.Next'
	replacements['.Length'] = '.length'
	replacements['boolean?'] = 'Boolean'
	replacements['int?'] = 'Integer'
	replacements['addRange'] = 'addAll'
	replacements['.Clear'] = '.clear'
	replacements['IEnumerable'] = 'List'
	replacements['ICollection'] = 'List'
	for key in replacements.keys():
		if key in line and (not replacements[key] in line or replacements[key] == '' or replacements[key] == 'class'):
			line = line.replace(key,replacements[key])
	if 'public' in line and 'get' in line:
		line = line.split('{')[0]+";\r"
	if 'Math.' in line and not '.PI' in line:
		for kill in [m.start() for m in re.finditer('Math.', line)]:
			killLoc = kill + 5
			l = list(line)
			l[killLoc] = line[killLoc].lower()
			line = "".join(l)
	if "override" in line:
		line = line.replace('override','')
		line = '@Override\r' + line		
	return line


# Phases
#   0 - Determine the namespace and prepare a location for the converted file
#   1 - Reorganize namespace and usings to match package and import format
#   2 - Simple replacement of keywords that map between c# and java
#   3 - Simple movement of method calls

def transform(path):
	for root,dirs,files in os.walk(path):
		if isCodeDir(root):
			print root
			for dir in dirs:
				transform(os.path.join(root,dir))
			for file in files:
				if isCodeOnly(file):
					phase = 0
					namespace = ""
					package = ""
					convert_file = ""
					while phase < 4:
						if phase == 0:
							for line in open(os.path.join(root,file),"r").read().splitlines():
								if "namespace" in line:
									namespace = line.split(' ')[1].lower().replace("ogur",'aigilas')

						if phase == 1:
							lib = ""
							lib2 = ""
							lib = namespace.split('.')[0].rstrip().lower()
							if len(namespace.split('.')) > 1:							
								lib2 = namespace.split('.')[1].rstrip().lower()
							package = os.path.join(os.path.join(os.path.join(convert_path,"com"),lib),lib2)
							if not os.path.exists(package):
								os.makedirs(package)
							convert_file = os.path.join(package,file.replace('.cs','.java'))

							w = open(convert_file,'w')
							w.write("package "+package.replace('\\','.').replace("..convert.",'')+';\r')
							w.write('import com.xna.wrapper.*;\r')
							w.write('import java.util.*;\r')
							braceCount = 0
							firstBraceFound = False
							for line in open(os.path.join(root,file),"r").read().splitlines():
								if '{' in line:
									braceCount += 1
								if '}' in line:
									braceCount -= 1
								if "using " in line:
									using = line.lower().replace("using ",'')
									if "SPX" in line:
										using = 'import ' + using.replace('spx','com.spx').replace(';','').rstrip() + ".*;\r"
										w.write(using)
									elif "OGUR" in line or "Aigilas" in line:
										using = 'import ' + using.replace('aigilas','com.aigilas').replace(';','').rstrip() + ".*;\r"
										w.write(using)
								else:
									excludes = ['namespace']
									allow = True
									for ex in excludes:
										if ex in line:
											allow = False
									if allow:
										if not firstBraceFound and braceCount > 0:
											firstBraceFound = True
										elif firstBraceFound and braceCount == 0:
											continue
										elif "#" in line:
											continue
										else:
											w.write(line + "\r")
							w.close()

						if phase == 2:		
							shutil.copyfile(convert_file,convert_file+'b')				
							w = open(convert_file,'w')
							for line in open(convert_file+'b','r').read().splitlines():
								w.write(cs2java(line)+"\r")
							w.close()
							os.remove(convert_file+'b')
						phase = phase + 1
						
						if phase == 3:
							shutil.copyfile(convert_file,convert_file+'b')	
							w = open(convert_file,'w')
							superCall = '';
							for line in open(convert_file+'b','r').read().splitlines():
								if superCall != '' and "{" in line:
									line = "{\r" + superCall.rstrip() + ';\r'
									superCall = ''
								if ":" in line:
									if 'super' in line:
										line,superCall = line.split(':')
									if 'class' in line:
										line = line.replace(":",' extends ')
								w.write(cs2java(line) + "\r")
							w.close()
							os.remove(convert_file+'b')

def scrub(path):
	for root,dirs,files in os.walk(path):
		if isCodeDir(root):
			print root
			for dir in dirs:
				scrub(os.path.join(root,dir))
			for file in files:
				if isCodeOnly(file):
					file = os.path.join(root,file)
					shutil.copyfile(file,file+'b')	
					w = open(file,'w')
					count = 0
					for line in open(file+'b','r').read().splitlines():
						if ';' in line and len(line) < 3:
							print 'Scrubbed'
						if 'target);;' in line:
							w.write(line.replace(';;','') + '\r')
						else:						
							w.write(line + '\r')
					w.close()
					os.remove(file+'b')

if 'gen' in sys.argv[1]:
	print '== Generating all starting java code'
	if os.path.exists(convert_path):		
		shutil.rmtree(os.path.join(convert_path,''))
	transform(start_path)
elif 'div' in sys.argv[1]:					
	print '== Splitting chunky file into multiple class files.'
	targetName = sys.argv[2]
	targetFile = sys.argv[3]
	targetUrl = sys.argv[4]
	divide_class(targetName,targetFile,targetUrl)
elif 'scrub' in sys.argv[1]:
	scrub(convert_path)
else:
	print 'Unrecognized option: ' + sys.argv[0]


